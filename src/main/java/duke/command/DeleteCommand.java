package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represent a "Delete" Command.
 * A <code>DeleteCommand</code> object corresponds to a command with an input of "Delete"
 * followed by the index of the task to be deleted
 */
public class DeleteCommand extends Command {

    private int index;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTask(index);
        taskList.removeTask(task);
        storage.delete(task);
        storage.update();
        return ui.printDeleteTask(taskList, task);
    }
}
