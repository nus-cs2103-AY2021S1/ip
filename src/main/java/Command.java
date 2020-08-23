public abstract class Command {
    private CommandType commandType;
    
    public boolean isExit() {
        return commandType == CommandType.BYE;
    }
    
    public abstract void execute(Tasks tasks, Ui ui, Storage storage) throws DukeException;
}
