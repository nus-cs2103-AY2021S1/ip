package command;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.IOException;

/**
 * Represents the delete command. The delete command removes a task from the task list.
 */
public class DeleteCommand extends Command {
    /** Integer representing the task number to be deleted. */
    private final int taskNum;

    /**
     * Creates a new Delete command. 
     *
     * @param taskNum Task number of task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete command. The execution involves deleting the task from the task list, writing to the storage as well as printing the relevant UI.
     *
     * @param lst List containing the current tasks.
     * @param ui Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.    
     */
    public void execute(TaskList lst, Ui ui, Storage storage) {
        try {
            int lineNum = taskNum - 1;
            Task task = lst.get(lineNum);
            storage.deleteLine(lineNum);
            lst.remove(task);
            ui.showDeleteTask(task, taskNum);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
