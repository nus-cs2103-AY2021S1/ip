package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

/**
 * Represents a ListCommand and handles methods related to commands
 * about displaying the list of tasks to users.
 */
public class ListCommand extends Command {

    /**
     * Constructs a listCommand object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the ListCommand by sending user the list
     * of tasks.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ui.getList();
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
