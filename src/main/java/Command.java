/**
 * Represents a specific command for duke to take in.
 */
public abstract class Command {
    /**
     * Executes the specific command given.
     * @param taskList tasklist containing all tasks.
     * @param ui user interace to print.
     * @param storage data stored backend.
     */
    public abstract void execute(TaskList taskList, Ui ui, DukeStorage storage);

    /**
     * Checks if duke should stop taking in commands.
     * @return true if the BYE command is inputted
     */
    public abstract boolean isCompleted();
}
