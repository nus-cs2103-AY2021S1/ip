package task;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskDateTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        LocalDateTime otherTaskTiming;
        if (otherTask instanceof Deadline) {
            otherTaskTiming = ((Deadline) otherTask).getTiming();
        } else if (otherTask instanceof Event) {
            otherTaskTiming = ((Event) otherTask).getTiming();
        } else {
            return -1;
        }

        LocalDateTime taskTiming;
        if (task instanceof Deadline) {
            taskTiming = ((Deadline) task).getTiming();
        } else if (task instanceof Event) {
            taskTiming = ((Event) task).getTiming();
        } else {
            return 1;
        }


        if (taskTiming.isAfter(otherTaskTiming)) {
            return 1;
        } else if (taskTiming.isBefore(otherTaskTiming)) {
            return -1;
        } else {
            return 0;
        }
    }
}
