package clippy.command;

import clippy.storage.Storage;

import clippy.task.Task;
import clippy.task.TaskList;

import clippy.ui.Ui;

/**
 * Represents a command that marks a task as done when executed.
 */
public class DoneCommand extends Command {
    private int indexOfDoneTask;

    /**
     * Constructs a command that marks a task as done when executed.
     * 
     * @param indexOfDoneTask User specified index of task to be marked as done.
     */
    public DoneCommand(int indexOfDoneTask) {
        this.indexOfDoneTask = indexOfDoneTask;
    }

    /**
     * Returns resulting message to be displayed by GUI after marking the task as done.
     * Executes the command.
     * Save changes onto save file.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI after marking the task as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask = tasks.getTask(indexOfDoneTask);
        doneTask.markAsDone();
        
        String output = ui.showDone(doneTask);
        
        storage.save(tasks);
        
        return output;
    }

}
