import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list that keeps the information of user's tasks.
 */
public class TaskList {
    public static List<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Prints the tasklist on screen.
     *
     * @param list The tasklist to be printed.
     * @return The string message converted from the tasklist.
     */
    public static String printList(List<Task> list) {
        int size = list.size();
        String string = "";
        for (int i = 1; i <= size; i++) {
            string += "\n"+i+". "+list.get(i-1);
        }
        return string;
    }
}
