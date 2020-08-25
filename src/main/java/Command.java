import java.io.IOException;

/**
 * Abstract class to represent permissible commands on the program.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes the given command.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     * @throws BlankTaskException If task name is empty when adding tasks.
     * @throws IOException        If an error occurred while saving the task to disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BlankTaskException, IOException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Exits the program after the command.
     */
    public void exit() {
        isExit = true;
    }
}
