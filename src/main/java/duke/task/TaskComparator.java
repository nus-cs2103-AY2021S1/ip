package duke.task;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        LocalDateTime first = o1.getDateTime();
        LocalDateTime second = o2.getDateTime();
        return first.isBefore(second)
                ? -1
                : first.isEqual(second)
                    ? 0
                    : 1;
    }

}
