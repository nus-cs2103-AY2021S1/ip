public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
