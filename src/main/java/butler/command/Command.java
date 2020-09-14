package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command that interacts with a <code>TaskList</code>.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores the given <code>taskList</code> on hard disk.
     * @return String response of task execution.
     * @throws ButlerException if an error occurs in the execution of this command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException;

    /**
     * Returns true if this command is an <code>ExitCommand</code>.
     *
     * @return <code>true</code> if this is an <code>ExitCommand</code>
     *         else <code>false</code>.
     */
    public abstract Boolean isExit();
}
