package duke;

/**
 * Represents a Command that can be executed by Duke.
 */
public abstract class Command {

    public Command() {}

    public abstract String execute(Tasklist tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
