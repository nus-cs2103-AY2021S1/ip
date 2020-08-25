public class Command {
    private String cmd;
    
    Command(String cmd) {
        this.cmd = cmd;
    }
    
    Command() {
        
    }
    
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        ui.showUnrecognizedCommandMessage();
    }
}
