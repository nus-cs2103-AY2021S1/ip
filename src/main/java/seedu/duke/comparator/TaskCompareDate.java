package seedu.duke.comparator;

import seedu.duke.task.Task;
import java.util.Comparator;

/**
 * Comparator for comparing the deadlines of each task.
 */
public class TaskCompareDate implements Comparator<Task> {
    /**
     * Compares two tasks according to the starting times of each task.
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getType() == "T") {
            return -1;
        } else if (t1.getType() == "E") {
            if (t2.getType() == "T" || t1.getTime().isAfter(t2.getTime())) {
                return 1;
            } else if (t2.getType() == "D" || t1.getTime().isBefore(t2.getTime())) {
                return -1;
            } else {
                    return 0;
            }
        } else {
            if (t2.getType() == "T" || t2.getType() == "E" || t1.getTime().isAfter(t2.getTime())) {
                return 1;
            } else if (t1.getTime().isBefore(t2.getTime())) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
