package duke.command;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an ErrorCommand and handles methods related to
 * sending error message to users.
 */
public class ErrorCommand extends Command {
    private String errorMessage;

    /**
     * Constructs an ErrorCommand.
     *
     * @param errorMessage The error message to be sent to users.
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the ErrorCommand by sending the error message to the users.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @return Duke's response.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        return ui.displayError(errorMessage);
    }

    /**
     * Checks if the command is ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


