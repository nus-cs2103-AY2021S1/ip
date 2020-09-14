package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command to list all tasks in a task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command to list all tasks in a task list.
     */
    public ListCommand() {
    }

    /**
     * Lists out the tasks in <code>taskList</code> using <code>ui</code> and
     * updates the list of tasks saved in the hard disk.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an error with saving the list of tasks occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        storage.storeTaskList(taskList);
        return ui.showTaskList(taskList);
    }

    /**
     * Returns true if this command is an <code>ExitCommand</code>.
     *
     * @return <code>false</code>.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
