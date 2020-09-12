package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command to exit Butler.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a command to exit Butler.
     */
    public ExitCommand() {
    }

    /**
     * Alerts the user that Butler will be closed and updates the task list saved in the hard disk.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        storage.storeTaskList(taskList);
        return ui.showExit();
    }

    /**
     * Returns a boolean whether this command is an <code>ExitCommand</code>.
     *
     * @return <code>true</code>
     */
    @Override
    public Boolean isExit() {
        return true;
    }
}
