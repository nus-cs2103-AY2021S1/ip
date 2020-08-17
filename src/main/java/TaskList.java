import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> taskList;

    TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String task){
        taskList.add(task);
    }

    public void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
}
