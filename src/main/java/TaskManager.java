import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> toDoList;
    public TaskManager() {
        this.toDoList = new ArrayList<>();
    }

    public void addToList(String task) {
        this.toDoList.add(new Task(task));
    }

    public void displayList() {
        System.out.println("Check out your missions!");
        int i = 1;
        for (Task s : this.toDoList) {
            System.out.println(i + ". " + s.toString() + " [" + s.getTaskStatusIcon() + "]");
            i += 1;
        }
    }

    public void checkList(int checkNumber) {
        Task task = toDoList.get(checkNumber - 1);
        task.markAsDone();
        System.out.println("--------------------------------------");
        System.out.println("Such wow! I have completed the following task!");
        System.out.println(task.toString() + " [" + task.getTaskStatusIcon() + "]");
        System.out.println("--------------------------------------");
    }

    public void taskPrint(String msg) {
        System.out.println("Added : " + msg);
    }
}
