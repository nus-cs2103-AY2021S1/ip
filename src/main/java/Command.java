public abstract class Command {
    abstract void executeCommand(TaskList tasks, Ui ui, Storage storage);
    
    public boolean isExit() {
        return false;
    }
}
