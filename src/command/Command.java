package command;

import exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command. A <code>Command</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public abstract class Command {
    protected String[] splitCommand;

    public Command(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    /**
     * Executes the <code>Command</code> object, in which the execution is based on the type of the <code>Command</code>
     * object.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @throws DukeException If the <code>splitCommand</code> is not compatible with the execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the <code>Command</code> object indicates exiting the Duke.
     *
     * @return <code>true</code> if the <code>Command</code> object indicates exiting the Duke, <code>false</code>
     * otherwise.
     */
    public abstract boolean isExit();
}
