package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to terminate Duke.
 */
public class ByeCommand extends Command {

    /**
     * Exit the program and saves the information to a file.
     * @param taskList The list of tasks stored by Duke.
     * @param ui The user interface to read inputs from the user and print messages.
     * @param storage The class that deals with saving tasks into the file and loading tasks
     *                from the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.storelist(taskList);
        ui.farewell();
        
    }

    /**
     * Indicates Duke should not keep running after this command is executed.
     * @return false.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
