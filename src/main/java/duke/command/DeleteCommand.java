package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a DeleteCommand where user wants to delete a task.
 */
public class DeleteCommand extends Command {

    private int i;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param i Index of task to be deleted.
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Executes the command to delete a task from list of tasks.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @throws DukeException If command is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.tasks.get(i - 1);
            tasks.tasks.remove(i - 1);
            ui.showAction(String.format("\t Noted. I've removed this task:\n"
                    + "\t   %s\n"
                    + "\t Now you have %d tasks in the list.\n", t, tasks.numTasks()));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't delete a task that does not exist.");
        }
        storage.save(tasks);
    }
}
