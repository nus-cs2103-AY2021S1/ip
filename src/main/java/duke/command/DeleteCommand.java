package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the AddCommand when users add items to TaskList.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand.
     *
     * @param index index of task
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param taskList taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage that handles data storage
     * @throws DukeException DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(index, storage);
    }

    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
