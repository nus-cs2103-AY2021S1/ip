public class ByeCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbyeUser();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
