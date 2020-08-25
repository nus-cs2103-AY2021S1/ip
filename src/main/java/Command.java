public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DoneException, DeleteException, DeadlineException, EventException, TodoException;
    public abstract boolean isExit();
}
