/**
 * The DoneCommand class, when executed, runs the steps required to mark a Task in a TaskList as done.
 *
 * @author Jaya Rengam
 */
package cartona.command;

import cartona.Storage;
import cartona.exception.CartonaException;
import cartona.task.Task;
import cartona.task.TaskList;
import cartona.ui.Ui;

public class DoneCommand implements Command {
    private boolean hasExecuted;

    /** The ID in the list of the task to be marked as complete. */
    private int taskIdToComplete;

    DoneCommand(int taskIdToComplete) {
        this.hasExecuted = false;
        this.taskIdToComplete = taskIdToComplete;
    }

    /**
     * Completes the Task given by taskIdToComplete in the TaskList and updates the Storage text file.
     *
     * @param taskList the TaskList being modified
     * @param ui the Ui object that is used to print the action to the console
     * @param storage the Storage object used to update the text file
     * @throws CartonaException if the command has already been executed
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (hasExecuted) {
            throw new CartonaException("Error: DoneCommand already executed");
        }

        taskList.completeTask(taskIdToComplete);
        Task completedTask = taskList.getTask(taskIdToComplete);

        // Update Storage
        storage.saveListToFile(taskList);

        // Return UI message
        return ui.printTaskDoneMessage(completedTask);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
