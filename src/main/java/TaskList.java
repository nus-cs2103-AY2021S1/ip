import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected static List<Task> toDoList = new ArrayList<>();
    /*public TaskList() {
        this.toDoList = new ArrayList<>();
    }*/
    public static void addToList(Task task) {
        toDoList.add(task);
    }

    public static void removeFromList(int taskId) {
        toDoList.remove(taskId - 1);
    }
}
