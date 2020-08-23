package duke.component;

import duke.task.Task;

import java.util.ArrayList;
import java.util.function.Predicate;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public int print(Predicate<Task> predicate) {
        int n = size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            Task task = get(i);
            if (predicate.test(task)) {
                System.out.println("\t  " + (i + 1) + "." + task);
                count++;
            }
        }
        return count;
    }
}
