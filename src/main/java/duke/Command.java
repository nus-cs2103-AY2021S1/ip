package duke;

/**
 * Encapsulates an abstract Command class.
 */
public abstract class Command {

    /**
     * Abstract method for executing a command.
     *
     * @param tasks   TaskList containing the tasks.
     * @param storage To read and write to file.
     * @param ui      Interact with user.
     * @throws DukeException If input does not meet requirements.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    /**
     * Checks for the termination of the programme.
     *
     * @return Boolean to indicate the termination of programme.
     */
    public boolean isExit() {
        return false;
    }
}
