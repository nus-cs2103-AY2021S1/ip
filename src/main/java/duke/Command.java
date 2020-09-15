package duke;

/**
 * A representation for user commands.
 */
public abstract class Command {
    protected String description;

    /**
     * Updates and stores tasks, prints out user response.
     * @param tasks List of the current tasks.
     * @param ui Ui object.
     * @param storage Stored copy of tasks.
     * @return The reply message from Duke.
     * @throws DukeException If errors occur for any of the processes involved.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * @return True if exit command.
     */
    public abstract boolean isExit();
}
