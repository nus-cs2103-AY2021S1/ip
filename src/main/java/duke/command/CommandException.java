package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to display error messages.
 */
public class CommandException extends Command {
    private String errMessage;

    /**
     * Creates a CommandException to display error messages.
     */
    public CommandException(String errMessage) {
        this.errMessage = errMessage;
    }

    /**
     * Displays error messages (if any) to user.
     *
     * @param ui Ui object to display messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of tasks.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return ui.showErrorMsg(errMessage);
    }

}
