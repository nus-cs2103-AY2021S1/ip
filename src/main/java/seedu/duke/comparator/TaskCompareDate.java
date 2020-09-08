package seedu.duke.comparator;

import seedu.duke.task.Task;

import java.util.Comparator;

public class TaskCompareDate implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getType() == "T") {
            return -1;
        } else if (t1.getType() == "E") {
            if (t2.getType() == "T") {
                return 1;
            } else if (t2.getType() == "D") {
                return -1;
            } else {
                if (t1.getTime().isBefore(t2.getTime())) {
                    return -1;
                } else if (t1.getTime().isAfter(t2.getTime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            if (t2.getType() == "T" || t2.getType() == "E") {
                return 1;
            } else {
                if (t1.getTime().isBefore(t2.getTime())) {
                    return -1;
                } else if (t1.getTime().isAfter(t2.getTime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
