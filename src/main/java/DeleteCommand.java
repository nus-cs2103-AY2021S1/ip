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
     * @param storage Storage object to handle saving of outputs to computer
     * @throws IndexOutOfBoundsDukeException If task number is invalid.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsDukeException {
        ui.showMessage(tasks.deleteTask(taskNumber));
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
