package clippy.command;

import clippy.exception.UpdateToDoTimeException;

import clippy.storage.Storage;

import clippy.task.Task;
import clippy.task.TaskList;

import clippy.ui.Ui;

/**
 * Represents a command to update the description and time of a task in the TaskList.
 */
public class UpdateDescriptionAndTimeCommand extends UpdateCommand {
    private String newDescription;
    private String newTime;

    /**
     * Constructs a command to update the description and time of a task in the TaskList.
     * 
     * @param indexOfTaskToUpdate User-specified index of task to be updated.
     * @param newDescription User-specified new literal description of the task.
     * @param newTime User-specified new time of the task.
     */
    public UpdateDescriptionAndTimeCommand(int indexOfTaskToUpdate, String newDescription, String newTime) {
        super(indexOfTaskToUpdate);
        this.newDescription = newDescription;
        this.newTime = newTime;
    }

    /**
     * Updates the description and time of the task at the specified index.
     * Executes the command.
     * Save changes onto the save file.
     * 
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message of the update to be shown on GUI.
     * @throws UpdateToDoTimeException If the task at the specified index is a todo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UpdateToDoTimeException {
        Task taskToUpdate = tasks.getTask(indexOfTaskToUpdate);
        
        taskToUpdate.updateTime(newTime);
        taskToUpdate.updateDescription(newDescription);
        
        storage.save(tasks);

        return ui.showUpdated(indexOfTaskToUpdate, taskToUpdate);
    }
}
