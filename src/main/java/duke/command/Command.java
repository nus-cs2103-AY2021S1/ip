package duke.command;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command and handles methods related to commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @return Duke's response.
     * @throws InvalidInputException If the input is invalid.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException;

    /**
     * Checks if the command is ExitCommand.
     *
     * @return True if it is ExitCommand; otherwise false.
     */
    public abstract boolean isExit();

}
