import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(List<Task> list) {
        this.list = list;
    }

    public static String printList(List<Task> list) {
        int size = list.size();
        String string = "";
        for (int i = 1; i <= size; i++) {
            string += "\n"+i+". "+list.get(i-1);
        }
        return string;
    }
}
