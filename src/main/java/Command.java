/**
 * Abstract class for handling different commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws DoneException, DeleteException, DeadlineException, EventException, TodoException, FindException;
    public abstract boolean isExit();
}
