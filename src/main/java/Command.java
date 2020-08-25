public abstract class Command {

    // private String command;
    
    // public Command(String command) {
    //     this.command = command;
    // }

    public abstract void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}