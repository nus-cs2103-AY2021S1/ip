public abstract class Command {
    private boolean shouldLoop;
    
    Command(boolean shouldLoop) {
        this.shouldLoop = shouldLoop;
    }
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public boolean shouldLoop() {
        return this.shouldLoop;
    }
}