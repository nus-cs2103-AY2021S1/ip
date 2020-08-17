import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    public Task getTask(int index) {
        // index is bounded from 0 to len - 1
        return taskList.get(index - 1);
    }

    public int numTasks() {
        return taskList.size();
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
}
