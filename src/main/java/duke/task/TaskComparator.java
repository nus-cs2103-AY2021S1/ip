package duke.task;

import java.util.Comparator;

/**
 * A class that compares two tasks by their type.
 */
public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task task, Task otherTask) {

        if (task instanceof Todo && otherTask instanceof Deadline) {
            return -1;
        } else if (task instanceof Todo && otherTask instanceof Event) {
            return -1;
        } else if (task instanceof Deadline && otherTask instanceof Event) {
            return -1;
        } else {
            return 0;
        }
    }
}
