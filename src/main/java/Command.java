import java.io.IOException;

/**
 * Abstract class to represent permissible commands on the program.
 */
public abstract class Command {

    /**
     * Executes the given command.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     * @return output string
     * @throws BlankTaskException If task name is empty when adding tasks.
     * @throws IOException        If an error occurred while saving the task to disk.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BlankTaskException, IOException;

}
