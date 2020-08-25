public class ExitCommand implements Command {
    
    public ExitCommand() {
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.bidFarewell();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
