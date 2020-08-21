import java.util.ArrayList;
import java.util.function.Predicate;

public class TaskList extends ArrayList<Task> {
    int count;

    public void print(Predicate<Task> predicate) {
        for (int i = 0; i < count; i++) {
            Task task = get(i);
            if (predicate.test(task)) {
                System.out.println("\t  " + (i + 1) + "." + task);
            }
        }
    }
}
