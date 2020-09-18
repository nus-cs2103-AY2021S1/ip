/**
 * Represents a generic command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks list of tasks.
     * @param ui user interface for printing relevant messages for the user.
     * @param storage file storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command is an exit command.
     * @return true if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
