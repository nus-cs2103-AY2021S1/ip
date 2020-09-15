package duke.parts;

import java.util.Comparator;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Comparator that is used to compare tasks when sorting the list
 *
 * @author Roger Lim
 */
public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo && t2 instanceof ToDo) {
            return 1;
        }
        if (t1 instanceof ToDo) {
            return -1;
        } else if (t2 instanceof ToDo) {
            return 1;
        }
        if (t1 instanceof Deadline && t2 instanceof Deadline) {
            Deadline d1 = (Deadline) t1;
            Deadline d2 = (Deadline) t2;
            return d1.getTime().compareTo(d2.getTime());
        } else if (t1 instanceof Deadline) {
            return -1;
        } else if (t2 instanceof Deadline) {
            return 1;
        }
        assert t1 instanceof Event && t2 instanceof Event;
        if (t1 instanceof Event && t2 instanceof Event) {
            Event e1 = (Event) t1;
            Event e2 = (Event) t2;
            return e1.getTime().compareTo(e2.getTime());
        }
        return 0;
    }
}
