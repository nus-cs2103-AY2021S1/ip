public class ExitCommand extends Command {

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    @Override
    protected boolean isExit() {
        return true;
    }
}
