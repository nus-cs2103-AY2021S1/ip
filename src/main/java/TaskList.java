import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected static List<Task> TO_DO_LIST = new ArrayList<>();
    /*public TaskList() {
        this.toDoList = new ArrayList<>();
    }*/
    public static void addToList(Task task) {
        TO_DO_LIST.add(task);
    }

    public static void removeFromList(int taskId) {
        TO_DO_LIST.remove(taskId - 1);
    }
}
