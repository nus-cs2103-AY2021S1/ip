import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (String task : tasks) {
            taskList.add(Task.textToTask(task));
        }
    }

    protected int getSize() {
        return this.taskList.size();
    }

    protected void addToDo(String description, Storage storage) {
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + toDo + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    protected void addDeadline(String description, LocalDateTime date, Storage storage) {
        Task deadline = new Deadline(description, date);
        taskList.add(deadline);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + deadline + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    protected void addEvent(String description, LocalDateTime date, Storage storage) {
        Task event = new Event(description, date);
        taskList.add(event);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + event + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    protected void deleteTask(int index, Storage storage) {
        Task taskToBeDeleted = taskList.get(index - 1);
        taskList.remove(index - 1);
        storage.saveData(taskList);
        String deletedTask = "\tRoger that! I've removed this task:\n\t\t"
                + taskToBeDeleted
                + "\n\tYou have " + taskList.size()
                + " tasks on your list now.\n";
        System.out.print(deletedTask);
    }

    protected void markTaskDone(int index, Storage storage) {
        Task finishedTask = taskList.get(index - 1);
        finishedTask.markAsDone();
        storage.saveData(taskList);
        String doneTask = "\t\\(^O^)/ Good job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n";
        System.out.print(doneTask);
    }

    protected void listTasks() {
        System.out.print("\tHere are the tasks on your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ". " + taskList.get(i) + "\n");
        }
    }
}
