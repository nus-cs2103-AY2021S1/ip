import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String taskDesc){
        taskList.add(new Task(taskDesc));
    }

    public Task getTask(int index) {
        // index is bounded from 0 to len - 1
        return taskList.get(index - 1);
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
}
