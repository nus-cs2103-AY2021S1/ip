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
     * @param tasks The list of tasks stored by Duke.
     * @param ui The user interface to read inputs from the user and print messages.
     * @param storage The class that deals with saving tasks into the file and loading tasks
     *                from the file.
     * @return A string representing the goodbye message when Duke is shut down.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.farewell();
    }

    /**
     * Indicates Duke should not keep running after this command is executed.
     * @return false.
     */
    @Override
    public boolean continueRunning() {
        return false;
    }
}
