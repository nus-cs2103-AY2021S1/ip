public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
