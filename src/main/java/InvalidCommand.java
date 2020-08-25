public class InvalidCommand extends Command {

    public InvalidCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.sendMessage("I don't know what you just sent...");
    }
}
