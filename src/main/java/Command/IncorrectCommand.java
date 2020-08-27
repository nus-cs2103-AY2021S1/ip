package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * IncorrectCommand is the command that informs the Ui to display error messages to
 * the user.
 *
 * @author Joshua
 */
public class IncorrectCommand extends Command {
    /**
     * This is the error message to be displayed to the user.
     */
    private String errorMessage;

    /**
     * Creates an IncorrectCommand that will produce an error message.
     *
     * @param errorMessage the error message to be produced.
     */
    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns true if command terminates Duke.
     *
     * @return the boolean to continue Duke.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the IncorrectCommand by updated the Ui with the message to be displayed
     * to the user as an error message. TaskList and Storage will not be affected.
     *
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }
}
