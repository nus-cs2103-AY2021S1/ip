package duke.command;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a DoneCommand and handles methods related to commands
 * about marking a task as done.
 */
public class DoneCommand extends Command {

    private Task task;
    private int index;

    /**
     * Constructs an DoneCommand.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DoneCommand by displaying the message to users
     * about the task is marked as done, and update the storage and
     * the taskList.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @return Duke's response.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        Task task = taskList.getTask(index);
        taskList.setAsDone(index);
        storage.write(taskList.getListOfTasks());
        return ui.displayDone(task);
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

