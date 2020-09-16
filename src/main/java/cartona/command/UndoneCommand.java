/**
 * The UndoneCommand class, when executed, runs the steps required to mark a Task in a TaskList as NOT done.
 *
 * @author Jaya Rengam
 */
package cartona.command;

import cartona.Storage;
import cartona.exception.CartonaException;
import cartona.task.Task;
import cartona.task.TaskList;
import cartona.ui.Ui;

public class UndoneCommand implements Command {
    private boolean hasExecuted;

    /** The ID in the list of the task to be marked as complete. */
    private int taskIdToUncomplete;

    UndoneCommand(int taskIdToUncomplete) {
        this.hasExecuted = false;
        this.taskIdToUncomplete = taskIdToUncomplete;
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

        taskList.uncompleteTask(taskIdToUncomplete);
        Task uncompletedTask = taskList.getTask(taskIdToUncomplete);
        assert !(uncompletedTask.checkIfDone()) : "Task not uncompleted";

        // Update Storage
        storage.saveListToFile(taskList);

        // Return UI message
        return ui.printTaskDoneMessage(uncompletedTask);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
