/**
 * The DeleteCommand class, when executed, runs the steps required to delete a Task from a TaskList.
 *
 * @author Jaya Rengam
 */
public class DeleteCommand implements Command {
    private boolean hasExecuted;
    /** The ID in the list of the task to be deleted. */
    private int taskIdToDelete;

    DeleteCommand(int taskIdToDelete) {
        this.hasExecuted = false;
        this.taskIdToDelete = taskIdToDelete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (hasExecuted) {
            throw new CartonaException("Error: DeleteCommand already executed");
        }

        // Delete the task
        Task deletedTask = taskList.getTask(taskIdToDelete);
        taskList.deleteTask(taskIdToDelete);

        // Print UI message
        ui.printTaskDeletionMessage(deletedTask, taskList.getSize());

        // Update Storage
        storage.saveListToFile(taskList);

        this.hasExecuted = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
