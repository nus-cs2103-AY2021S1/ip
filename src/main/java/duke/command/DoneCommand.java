package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code DoneCommand} is a child of {@code Command} object.
 *      On execution, it will mark the given task in the task list as completed.
 */
public class DoneCommand extends Command {
    private int idx;

    /**
     * Constructs a DoneCommand which marks a task from the list as completed on execution.
     *
     * @param idx Index of the task to be marked as completed.
     */
    public DoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks the given task index as completed.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task t = tasks.get(idx);
            t.setDone();
            ui.showDone(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I cannot check this element: " + idx);
        }
    }
}
