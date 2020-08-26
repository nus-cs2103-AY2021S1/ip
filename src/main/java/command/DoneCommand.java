package command;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.IOException;

/**
 * Represents the done command. The done command marks a task from the task list as done.
 */
public class DoneCommand extends Command {
    /** Integer representing the task number to be marked as done. */
    private final int taskNum;

    /**
     * Creates a new Done command. 
     *
     * @param taskNum Task number of task to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the done command. The execution involves marking the task as done in the task list, writing to the storage as well as printing the relevant UI.
     *
     * @param lst List containing the current tasks.
     * @param ui Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.    
     */
    public void execute(TaskList lst, Ui ui, Storage storage) {
        int lineNum = taskNum - 1;
        Task task = lst.get(lineNum);
        task.markAsDone();
        try {
            storage.modifyLine(lineNum);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        ui.showDoneTask(task, taskNum);
    }
}
