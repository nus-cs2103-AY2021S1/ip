/**
 * Abstract command class to be extended
 */
public abstract class Command {
    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     */
    public abstract void execute(TaskList inputTasks, Storage storage) throws DukeException;
}
