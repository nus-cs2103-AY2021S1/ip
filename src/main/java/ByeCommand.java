public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
