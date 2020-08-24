public class ByeCommand extends Command {

    public ByeCommand() {
        this.isBye = true;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.bye();
    }
}