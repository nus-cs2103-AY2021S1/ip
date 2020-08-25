package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represent a "Delete" Command.
 * A <code>DeleteCommand</code> object corresponds to a command with an input of "Delete"
 * followed by the index of the task to be deleted
 */
public class DeleteCommand extends Command {

    int index;

    /**
     * Constructor of the DeleteCommand Class
     *
     * @param index The index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Retrieve task from taskList and remove the task from it.
     * Prints corresponding messages and deletes the task from the datafile
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        taskList.removeTask(task);
        ui.print("Noted. I've deleted this task:\n" + task);
        ui.print("Now you have " + (taskList.getList().size()) + " tasks in the list." );
        storage.delete(task);
    }
}
