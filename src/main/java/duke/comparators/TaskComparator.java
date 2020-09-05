package duke.comparators;

import java.util.Comparator;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        int taskOneIndex = getOrderIndex(o1);
        int taskTwoIndex = getOrderIndex(o2);

        if (taskOneIndex < taskTwoIndex) {
            return -1;
        } else if (taskOneIndex > taskTwoIndex) {
            return 1;
        } else {
            return 0;
        }
    }
    private int getOrderIndex(Task task) {
        if (task == null) {
            return 0;
        } else if (task instanceof Event || task instanceof Deadline) {
            // so events and deadlines comes before todo
            return 1;
        } else {
            // task is a task
            return 2;
        }
    }
}
