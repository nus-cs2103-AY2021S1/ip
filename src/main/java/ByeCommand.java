public class ByeCommand extends Command {
    private final CommandType commandType;
    
    public ByeCommand() {
        this.commandType = CommandType.BYE;
    }
    
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        System.exit(0);
    }
}
