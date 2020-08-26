package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the ListCommand when users wants to list out the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand.
     */
    public ListCommand() { }

    /**
     * Lists out the items on the TaskList.
     *
     * @param tasks taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage storage that handles data storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
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
