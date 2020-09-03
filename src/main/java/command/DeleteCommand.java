package command;

import java.io.IOException;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;


/**
 * Represents the delete command. The delete command removes a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * Integer representing the task number to be deleted.
     */
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
     * Executes the delete command. The execution involves deleting the task from the task list,
     * writing to the storage as well as printing the relevant UI.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     * @return String response by the application after executing the command.
     */
    public String execute(TaskList lst, Ui ui, Storage storage) {
        String result;
        try {
            int lineNum = taskNum - 1;
            Task task = lst.get(lineNum);
            storage.deleteLine(lineNum);
            lst.remove(task);
            result = ui.showDeleteTask(task, taskNum);
        } catch (IOException e) {
            result = ui.showError(e.getMessage());
        }
        return result;
    }
}
