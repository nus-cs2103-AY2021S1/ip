import java.util.Map;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {

    // Attributes
    private final int taskNumber;

    // Constructor
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    // Methods

    /**
     * Executes the command to mark a task as done in TaskList.
     * @param tasks TaskList representing list of current tasks.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer
     * @throws IndexOutOfBoundsDukeException If task number is invalid.
     */
    @Override
    String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables)
            throws IndexOutOfBoundsDukeException {
        return tasks.markAsDone(taskNumber);
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
