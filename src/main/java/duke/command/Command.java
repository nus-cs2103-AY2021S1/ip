package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

/**
 * The Command class handles methods related to commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param storage The storage object.
     * @param listOfTasks The list of tasks.
     * @param ui The ui object.
     * @throws InvalidInputException If the input is invalid.
     */
    public abstract void execute(Storage storage, TaskList listOfTasks, Ui ui) throws InvalidInputException;

    /**
     * Checks if the command is ExitCommand.
     *
     * @return True if it is ExitCommand; otherwise false.
     */
    public abstract boolean isExit();

}
