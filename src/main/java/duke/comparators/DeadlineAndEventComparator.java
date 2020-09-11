package duke.comparators;

import java.util.Comparator;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class DeadlineAndEventComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (o1 instanceof Deadline && o2 instanceof Deadline) {
            Deadline d1 = (Deadline) o1;
            Deadline d2 = (Deadline) o2;
            return d1.getDateTime().compareTo(d2.getDateTime());
        } else if (o1 instanceof Deadline && o2 instanceof Event) {
            Deadline d1 = (Deadline) o1;
            Event e2 = (Event) o2;
            return d1.getDateTime().compareTo(e2.getDateTime());
        } else if (o1 instanceof Event && o2 instanceof Deadline) {
            Event e1 = (Event) o1;
            Deadline d2 = (Deadline) o2;
            return e1.getDateTime().compareTo(d2.getDateTime());
        } else if (o1 instanceof Event && o2 instanceof Event) {
            Event e1 = (Event) o1;
            Event e2 = (Event) o2;
            return e1.getDateTime().compareTo(e2.getDateTime());
        } else {
            return 0;
        }
    }
}
