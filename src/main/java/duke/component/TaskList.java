package duke.component;

import java.util.ArrayList;
import java.util.function.Predicate;

import duke.task.Task;

/**
 * Represents lists of tasks.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Creates a list of tasks.
     */
    public TaskList() {
        super();
    }

    /**
     * Prints a filtered list based on this list with the given predicate on CLI App.
     * @param predicate the condition for a task to be printed
     * @return the number of tasks printed
     */
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

    /**
     * Prints a filtered list based on this list with the given predicate on FXML App.
     * @param initialStatement the heading about the content of the task list
     * @param predicate checks if a task is wanted to print
     * @return the entire string of the filtered task list
     */
    public String print(String initialStatement, Predicate<Task> predicate) {
        String res = initialStatement;
        int n = size();
        for (int i = 0; i < n; i++) {
            Task task = get(i);
            if (predicate.test(task)) {
                res = res.concat(getIndexedTaskString(i, task));
            }
        }
        return res;
    }

    private String getIndexedTaskString(int i, Task task) {
        return (i + 1) + "." + task + "\n";
    }
}
