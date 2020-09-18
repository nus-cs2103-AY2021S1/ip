package clippy.command;

import clippy.exception.UpdateToDoTimeException;
import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a command to update only the description of a task in the TaskList.
 */
public class UpdateDescriptionCommand extends UpdateCommand {
    private String newDescription;

    /**
     * Constructs a command to update only the description of a task in the TaskList.
     *
     * @param indexOfTaskToUpdate User-specified index of task to be updated.
     * @param newDescription User-specified new literal description of the task.
     */
    public UpdateDescriptionCommand(int indexOfTaskToUpdate, String newDescription) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
    }

    /**
     * Updates the description of the task at the specified index.
     * Executes the command.
     * Save changes onto the save file.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message of the update to be shown on GUI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        taskToUpdate.updateDescription(newDescription);
        
        storage.save(tasks);
        
        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
