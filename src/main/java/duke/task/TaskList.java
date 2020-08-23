package duke.task;

import duke.parser.Parser;
import duke.storage.Storage;

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
            taskList.add(Parser.textToTask(task));
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void addToDo(String description, Storage storage) {
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + toDo + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    public void addDeadline(String description, LocalDateTime date, Storage storage) {
        Task deadline = new Deadline(description, date);
        taskList.add(deadline);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + deadline + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    public void addEvent(String description, LocalDateTime date, Storage storage) {
        Task event = new Event(description, date);
        taskList.add(event);
        storage.saveData(taskList);
        String printing = "\tGotcha! I've added this task:\n\t\t"
                + event + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        System.out.print(printing);
    }

    public void deleteTask(int index, Storage storage) {
        Task taskToBeDeleted = taskList.get(index - 1);
        taskList.remove(index - 1);
        storage.saveData(taskList);
        String deletedTask = "\tRoger that! I've removed this task:\n\t\t"
                + taskToBeDeleted
                + "\n\tYou have " + taskList.size()
                + " tasks on your list now.\n";
        System.out.print(deletedTask);
    }

    public void markTaskDone(int index, Storage storage) {
        Task finishedTask = taskList.get(index - 1);
        finishedTask.markAsDone();
        storage.saveData(taskList);
        String doneTask = "\t\\(^O^)/ Good job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n";
        System.out.print(doneTask);
    }

    public void listTasks() {
        System.out.print("\tHere are the tasks on your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ". " + taskList.get(i) + "\n");
        }
    }

    public void findTasks(String keyword) {
        boolean canFind = false;
        for (Task task : taskList) {
            if (task.getTaskName().contains(keyword)) {
                canFind = true;
                break;
            }
        }
        if (canFind) {
            int index = 1;
            System.out.print("\tHere are the matching tasks on your list:\n");
            for (Task task : taskList) {
                if (task.getTaskName().contains(keyword)) {
                    System.out.print("\t" + index + ". " + task + "\n");
                    index++;
                }
            }
        } else {
            System.out.print("\tSorry, there are no tasks that match your keyword!\n");
        }
    }
}
