import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private static final String indentation = "     ";

    TaskManager() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println(indentation + "added: " + task.getName());
    }

    public void printList() {
        for(int i = 0; i < taskList.size(); i++) {
            String s = indentation + (i + 1) + ". " + taskList.get(i).getName();
            System.out.println(s);
        }
    }
}
