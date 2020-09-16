package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * AddCommand will add the input task to the TaskList.
 *
 * @author Joshua
 */
public class AddCommand extends Command {
    /**
     * Creates an AddCommand with the input task.
     *
     * @param task the input task to be added to TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand with the following TaskList, Ui and Storage classes.
     * The input task will be added to the TaskList. The Ui will return the output
     * to the user. The storage will update with the new TaskList.
     *
     * @param taskList the TaskList to add the input task to.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is to be updated.
     * @return the output to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        output = ui.showAdded(task) + "\n";
        output = output + ui.showNumberInList(taskList);
        storage.updateStorage(taskList);
        return output;
    }
}
