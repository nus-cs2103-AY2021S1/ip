package duke;

/**
 * Base command class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList the task list used for the command.
     * @param storage  the storage used for the command.
     * @param ui ui where the command response is shown.
     * @throws DukeException duke failed to complete the command.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;
}
