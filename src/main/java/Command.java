/**
 * Represents a command that the user input into the system.
 */
public abstract class Command {
    /**
     * Executes the command by performing the steps required for that.
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException Throws the required exception as necessary.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Shows whether the command is an exit command or not.
     * @return True if the command is an exit command, false if it is not.
     */
    public abstract boolean isExit();
}
