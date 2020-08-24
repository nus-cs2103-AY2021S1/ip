package duke;

/**
 * Represents a command in Duke.
 */
public abstract class Command {

    /**
     * Checks if this object is an ExitCommand.
     *
     * @return <code>true</code> if this is ExitCommand instance, else false.
     */
    public boolean isExit() { return false; }

    /**
     * Executes the command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
