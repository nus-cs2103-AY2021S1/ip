package duke.exec;

import java.util.Comparator;
import java.util.List;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class SortCommand implements Executable {

    // string constants
    private static final String SORTED_MESSAGE = "Your list has been sorted. ";
    private static final String LIST_MESSAGE_FRONT = "There are currently ";
    private static final String LIST_MESSAGE_END = " tasks in your list.";

    // comparator constant
    private static final Comparator<Task> comparator = new Comparator<>() {
        @Override
        public int compare(Task t1, Task t2) {
            if (t1 instanceof Todo || t2 instanceof Todo) {
                return t1.compareTo(t2);
            }
            if (t1 instanceof Deadline && t2 instanceof Deadline) {
                Deadline temp = (Deadline) t1;
                Deadline tempTwo = (Deadline) t2;
                return temp.getDate().compareTo(tempTwo.getDate());
            }
            if (t1 instanceof Deadline && t2 instanceof Event) {
                Deadline temp = (Deadline) t1;
                Event tempTwo = (Event) t2;
                return temp.getDate().compareTo(tempTwo.getDate());
            }
            if (t1 instanceof Event && t2 instanceof Deadline) {
                Event temp = (Event) t1;
                Deadline tempTwo = (Deadline) t2;
                return temp.getDate().compareTo(tempTwo.getDate());
            }
            if (t1 instanceof Event && t2 instanceof Event) {
                Event temp = (Event) t1;
                Event tempTwo = (Event) t2;
                return temp.getDate().compareTo(tempTwo.getDate());
            }
            assert false : "Object not of type Task has entered"; // should not reach here
            return -1; // should not reach here
        }
    };

    /**
     * When run, sorts the items in the list of tasks based on lexicographical
     * ordering and if applicable, chronological ordering
     *
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     * @return the output after running this command
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage store) {
        if (tasks.size() > 1) {
            tasks.sort(comparator);
        }
        List<String> enumeration = tasks.enumerate();
        enumeration.add(0, SORTED_MESSAGE + "\n"
                + LIST_MESSAGE_FRONT + tasks.size() + LIST_MESSAGE_END);
        return ui.outputString(enumeration);
    }
}
