package duke;

/**
 * Base command class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList the tasklist used for the command.
     * @param storage  the storage used for the command.
     * @throws DukeException duke failed to complete the command.
     */
    public abstract void execute(TaskList taskList, Storage storage) throws DukeException;
}
