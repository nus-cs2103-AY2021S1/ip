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
        int i = 1;
        for (String s : this.toDoList) {
            System.out.println(i + "." + s);
            i += 1;
        }
    }

    public void taskPrint(String msg) {
        System.out.println("Added : re" + msg);
    }
}
