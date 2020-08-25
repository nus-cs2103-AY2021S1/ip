package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a DoneCommand where user wants to mark a task as done.
 */
public class DoneCommand extends Command {

    private int i;

    /**
     * Creates a DoneCommand.
     *
     * @param i Index of task to be marked as done.
     */
    public DoneCommand(int i) {
        this.i = i;
    }

    /**
     * Executes the command to mark a task as done.
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
            t.markAsDone();
            ui.showAction(String.format("\t Nice! I've marked this task as done:\n"
                    + "\t   %s\n", t));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't complete a task that does not exist.");
        }
        storage.save(tasks);
    }
}
