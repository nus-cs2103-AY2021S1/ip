package seedu.duke.comparator;

import seedu.duke.task.Task;
import java.util.Comparator;

/**
 * Comparator for comparing the completion status of each task.
 */
public class TaskCompareDone implements Comparator<Task> {

    /**
     * Compares two tasks according to the type of task they are, and their completion status.
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.isComplete() && !t2.isComplete()) {
            return -1;
        } else if (!t1.isComplete() && t2.isComplete()) {
            return 1;
        } else {
            if (t1.getName() == "T") {
                return -1;
            } else if (t1.getName() == "E") {
                if (t2.getName() == "T") {
                    return 1;
                } else if (t2.getName() == "D") {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                if (t2.getName() == "T" || t2.getName() == "E") {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

}
