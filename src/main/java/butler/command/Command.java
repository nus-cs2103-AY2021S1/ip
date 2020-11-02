package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskListManager;

/**
 * Represents a command that interacts with a list of tasks.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param taskListManager Manager of the list of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores current list of tasks on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an error occurs in the execution of this command.
     */
    public abstract String execute(TaskListManager taskListManager, Ui ui, Storage storage) throws ButlerException;

    /**
     * Returns true if this command is an <code>ExitCommand</code>.
     *
     * @return <code>true</code> if this is an <code>ExitCommand</code>
     *         else <code>false</code>.
     */
    public abstract Boolean isExit();
}
