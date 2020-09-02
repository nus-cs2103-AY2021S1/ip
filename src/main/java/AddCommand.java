/**
 * The AddCommand class, when executed, runs the steps required to add a Task to a TaskList.
 *
 * @author Jaya Rengam
 */
public class AddCommand implements Command {
    private boolean isDone;

    /** The task to be added */
    private Task taskToAdd;

    AddCommand(Task taskToAdd) {
        this.isDone = false;
        this.taskToAdd = taskToAdd;
    }

    /**
     * Adds the Task given by taskToAdd to the TaskList and updates the Storage text file.
     *
     * @param taskList the TaskList being modified
     * @param ui the Ui object that is used to print the action to the console
     * @param storage the Storage object used to update the text file
     * @throws CartonaException if the command has already been executed
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (isDone) {
            throw new CartonaException("Error: AddCommand already executed");
        }

        taskList.addTask(taskToAdd);

        this.isDone = true;

        // Print UI message
        ui.printTaskAddingMessage(taskToAdd, taskList.getSize());

        // Update Storage
        storage.saveListToFile(taskList);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}