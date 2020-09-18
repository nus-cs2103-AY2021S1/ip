package clippy.command;

import clippy.storage.Storage;

import clippy.task.Task;
import clippy.task.TaskList;

import clippy.ui.Ui;

/**
 * Represents a command that deletes a task when executed.
 */
public class DeleteCommand extends Command {
    private int indexOfTaskToDelete;

    /**
     * Constructs a command that deletes a task when executed.
     * 
     * @param indexOfTaskToDelete User specified index of task to be deleted.
     */
    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }

    /**
     * Returns resulting message to be displayed by GUI after deleting the task.
     * Executes the command.
     * Save changes onto save file.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI after deleting the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(indexOfTaskToDelete);
        tasks.delete(indexOfTaskToDelete);
        
        String output = ui.showDeleted(taskToDelete, tasks.getSize());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }

}
