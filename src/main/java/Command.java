/**
 * Abstract class for handling different commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
