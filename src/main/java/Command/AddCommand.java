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
     * Returns true if command terminates Duke.
     *
     * @return the boolean to continue Duke.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the AddCommand with the following TaskList, Ui and Storage classes.
     * The input task will be added to the TaskList. The Ui will inform the user of
     * the action. The storage will update with the new TaskList.
     *
     * @param taskList the TaskList to add the input task to.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is to be updated.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showAdded(task);
        ui.showNumberInList(taskList);
        storage.updateStorage(taskList);
    }
}
