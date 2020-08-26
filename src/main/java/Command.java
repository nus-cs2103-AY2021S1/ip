/**
 * Represents a generic command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks list of tasks
     * @param ui user interface for printing relevant messages for the user
     * @param storage file storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
