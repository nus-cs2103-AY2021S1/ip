package duke.command;

import java.util.Comparator;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to sort the Task in a TaskList.
 */
public class SortCommand extends Command {

    /** Custom Comparator which sorts the Tasks base on their types and then by their Date and Time */
    private static final Comparator<Task> TASK_COMPARATOR = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            if (o1 instanceof Todo) {
                return compareTodo(o2);
            } else if (o1 instanceof Deadline) {
                return compareDeadline((Deadline) o1, o2);
            } else {
                return compareEvent((Event) o1, o2);
            }
        }
    };

    /**
     * Constructs a <code>SortCommand</code> object.
     */
    public SortCommand() {
        super(false);
    }

    private static int compareTodo(Task t2) {
        if (t2 instanceof Todo) {
            return 0;
        }
        return -1;
    }

    private static int compareDeadline(Deadline d1, Task t2) {
        if (t2 instanceof Todo) {
            return 1;
        } else if (t2 instanceof Event) {
            return -1;
        } else {
            Deadline d2 = (Deadline) t2;
            return d1.getDateTime().compareTo(d2.getDateTime());
        }
    }

    private static int compareEvent(Event e1, Task t2) {
        if (t2 instanceof Event) {
            Event e2 = (Event) t2;
            return e1.getDateTime().compareTo(e2.getDateTime());
        }
        return 1;
    }

    /**
     * Sorts the Task in TaskList.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getList().sort(TASK_COMPARATOR);
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(createResponseMessage(tasks), shouldExit);
    }

    private String createResponseMessage(TaskList tasks) {
        return tasks.toString().replaceFirst(
                "Here are the tasks in your list",
                "Got it! I've sorted your tasks");
    }
}
