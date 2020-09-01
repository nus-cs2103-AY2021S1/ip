public class ByeCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.bye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
