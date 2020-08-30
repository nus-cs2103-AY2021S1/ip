package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

/**
 * Represents an ExitCommand and handles methods related to commands
 * about sending the exit message.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the ExitCommand by displaying the exit message to users.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ui.displayExit();
    }

    /**
     * Checks if the command is ExitCommand.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}



