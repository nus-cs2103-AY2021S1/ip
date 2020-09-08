package seedu.duke.comparator;

import seedu.duke.task.Task;

import java.util.Comparator;

public class TaskCompareDone implements Comparator<Task> {

    /**
     * Compares two tasks according to the type of task they are, and their completion status.
     * @param t1
     * @param t2
     * @return
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
