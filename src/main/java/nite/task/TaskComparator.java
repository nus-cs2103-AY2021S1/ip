package nite.task;

import java.util.Comparator;

/**
 * Type comparator compares two tasks of the same type.
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares two tasks of the same type.
     * The first criteria of  comparison is the time of the task.
     * If the task lacks a timing, or if two timings are the same,
     * tasks will be compared by alphabetical order.
     *
     * @param task1 The first task to be compared.
     * @param task2 The second task to be compared
     * @return The relative ranking of the two tasks.
     */
    @Override
    public int compare(Task task1, Task task2) {
        if (task1.typeOfTask().equals("todo")) {
            return task1.description.compareTo(task2.description);
        } else if (task1.typeOfTask().equals("deadline")
                || task1.typeOfTask().equals("event")) {
            if (task1.getTime().equals(task2.getTime())) {
                return task1.description.compareTo(task2.description);
            }
            return task1.getTime().compareTo(task2.getTime());
        }
        return 0;
    }
}
