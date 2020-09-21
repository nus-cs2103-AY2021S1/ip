package duke.command;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an AddCommand and handles methods related to commands about adding a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand.
     *
     * @param task The duke.task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the duke.task in to the list
     * and giving user messages. The storage file is also updated.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @return Duke's response.
     * @throws InvalidInputException If the input is invalid.
     */

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        taskList.addTask(task);
        storage.write(taskList.getListOfTasks());
        return ui.displayAddition(task);
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
