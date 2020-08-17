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
        int i = 1;
        for (Task s : this.toDoList) {
            System.out.println(i + "." + s.toString());
            i += 1;
        }
    }



    public void taskPrint(String msg) {
        System.out.println("Added : " + msg);
    }
}
