package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;
import duke.task.Task;

/**
 * Represents a DeleteCommand and handles methods related to commands
 * about deleting a task.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs an DeleteCommand.
     *
     * @param index The index of the duke.task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the AddCommand by deleting the duke.task from the list
     * and giving user messages. The storage file is also updated.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @throws InvalidInputException
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.write(taskList.getListOfTasks());
        ui.displayDeletion(task);
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

