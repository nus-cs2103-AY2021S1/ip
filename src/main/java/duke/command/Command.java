package duke;

/**
 * <p>duke.Command class defines the behaviour of a command entered by the user.</p>
 */
public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws DukeException;

    public abstract boolean isBye();
}
