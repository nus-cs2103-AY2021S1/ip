import java.util.Map;

/**
 * Class representing commands to delete a task
 */
public class DeleteCommand extends Command {

    // Attributes
    private final int taskNumber;

    // Constructor
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    // Methods

    /**
     * Executes the command to delete a task by deleting specified task from given TaskList.
     * @param tasks TaskList representing list of current tasks.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer.
     * @throws IndexOutOfBoundsDukeException If task number is invalid.
     */
    @Override
    String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables)
            throws IndexOutOfBoundsDukeException {
        assert tasks != null : "tasks shouldn't be null";
        assert ui != null : "ui shouldn't be null";
        assert storage != null : "storage shouldn't be null";
        assert runnables != null : "runnables shouldn't be null";

        return tasks.deleteTask(taskNumber);
    }

    /**
     * Returns whether the command is a command to exit.
     * @return false.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
