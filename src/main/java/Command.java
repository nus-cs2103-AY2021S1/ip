/**
 * Controls main logic of all user commands.
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     * @throws DukeException When date time in wrong format, or description not given, or I/O error.
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if should exit program.
     *
     * @return Whether or not to exit program.
     */
    abstract public boolean isExit();
}
