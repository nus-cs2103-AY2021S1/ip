public class ByeCommand extends Command {
    ByeCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }
    
    @Override
    public String execute() {
        return Ui.showGoodbyeMessage();
    }
}
