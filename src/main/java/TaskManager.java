import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<String> toDoList;
    public TaskManager() {
        this.toDoList = new ArrayList<>();
    }
    public void addToList(String task) {
        this.toDoList.add(task);
    }
    public void displayList() {
        for (String s : this.toDoList) {
            System.out.println(s);
        }
    }
}
