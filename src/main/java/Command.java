/**
 * Abstract command class to be extended
 */
public abstract class Command {
    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     * @throws DukeException throws exceptions that fail to fulfil command requirements
     */
    public abstract void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException;
}
