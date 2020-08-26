public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOnExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
