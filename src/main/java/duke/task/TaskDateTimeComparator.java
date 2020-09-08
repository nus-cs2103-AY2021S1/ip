package duke.task;

import java.util.Comparator;

/**
 * A class that compares two tasks by their date-time.
 */
public class TaskDateTimeComparator implements Comparator<Task> {

    @Override
    public int compare(Task task, Task otherTask) {

        if (task.getDate().isAfter(otherTask.getDate())) {
            return 1;
        } else if (task.getDate().isBefore(otherTask.getDate())) {
            return -1;
        } else {
            return 0;
        }
    }
}
