package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

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
     * @param taskList
     * @param ui The ui object.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ui.displayError(errorMessage);
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


