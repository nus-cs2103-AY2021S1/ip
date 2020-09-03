public abstract class Command {
    abstract String execute(TaskList tasks, Ui ui, Storage storage);
    
    abstract boolean isExit();
}
