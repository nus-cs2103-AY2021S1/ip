public abstract class Command {
    
    abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    
    boolean isExit() {
        return false;
    }
    
}
