package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code: DelelteCommand} is a child of {@code: Command} object.
 *      On execution, it will delete a given index from the task list.
 */
public class DeleteCommand extends Command {

    private int idx;

    /**
     * Constructs a DeleteCommand which deletes a task from the list on execution.
     *
     * @param idx Index of the task to be deleted.
     */
    public DeleteCommand (int idx) {
        this.idx = idx;
    }

    /**
     * Deletes the given task index from the task list.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task t = tasks.remove(idx);
            ui.showRemoveTask(t, tasks.size());
        } catch (IndexOutOfBoundsException iooob) {
            throw new DukeException("I cannot delete this element: " + idx);
        }
    }
}
