package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task in to the list
     * and giving user messages. The storage file is also updated.
     *
     * @param storage The storage object.
     * @param taskList
     * @param ui The ui object.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        taskList.addTask(task);
        storage.write(taskList.getListOfTasks());
        ui.displayAddition(task);
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
