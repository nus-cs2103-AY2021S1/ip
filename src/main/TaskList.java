import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTasks(){
        return taskList;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        String addTaskMessage = "Got it. I've added this task:\n" +
                newTask.toString() + "\n" +
                "Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ") + "in the list.\n";
        new Ui(addTaskMessage).printMessageWithBorders();
    }

    public void deleteTask(int index) {
        Task task = taskList.remove(index);
        String deleteTaskMessage = "Noted. I've removed this task:\n" +
                task.toString() + "\n";
        new Ui(deleteTaskMessage).printMessageWithBorders();
    }

    public void markDone(int i) {
        Task task = taskList.get(i);
        task.makeDone();
        String markDoneMessage = "Nice! I've marked this task as done:\n" +
                "[\u2713] " + task.getDescription() + "\n";
        new Ui(markDoneMessage).printMessageWithBorders();
    }
}
