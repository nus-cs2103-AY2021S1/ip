public abstract class Command {
    
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public abstract boolean isExit();
}

