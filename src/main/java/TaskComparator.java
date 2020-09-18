import java.util.Comparator;

/**
 * Implements a comparator for tasks to compare the dates of tasks.
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares the two tasks to determine which comes first.
     * The basis of comparison is the task's date.
     * @param task1 one of the two tasks that is being compared.
     * @param task2 the other task that is being compared.
     * @return an integer -1, 0 or 1.
     */
    @Override
    public int compare(Task task1, Task task2) {
        return task1.getDate().compareTo(task2.getDate());
    }
}
