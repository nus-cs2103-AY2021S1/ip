public class ByeCommand extends Command {

    @Override
    public boolean isRunning() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exit();
    }
}
