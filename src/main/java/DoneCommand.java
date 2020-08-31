/**
 * The DoneCommand class, when executed, runs the steps required to mark a Task in a TaskList as done.
 *
 * @author Jaya Rengam
 */
public class DoneCommand implements Command{
    private boolean hasExecuted;
    /** The ID in the list of the task to be mark as complete. */
    private int taskIdToComplete;

    DoneCommand(int taskIdToComplete) {
        this.hasExecuted = false;
        this.taskIdToComplete = taskIdToComplete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (hasExecuted) {
            throw new CartonaException("Error: DoneCommand already executed");
        }

        taskList.completeTask(taskIdToComplete);
        Task completedTask = taskList.getTask(taskIdToComplete);

        // Print UI message
        ui.printTaskDoneMessage(completedTask);

        // Update Storage
        storage.saveListToFile(taskList);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}