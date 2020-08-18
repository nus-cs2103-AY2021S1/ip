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
            String s = indentation + (i + 1) + ". " + taskList.get(i).toString();
            System.out.println(s);
        }
    }

    public void markTaskAsDone(int n) {
        System.out.println(indentation + "Nice! I've marked this task as done: ");
        Task task = taskList.get(n - 1);
        task.markAsDone();
        System.out.println(indentation + "  " + task.toString());
    }
}
