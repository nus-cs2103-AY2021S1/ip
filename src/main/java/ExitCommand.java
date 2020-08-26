public class ExitCommand extends Command {
    ExitCommand(String action) {
        super(action);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.sendOff();
    }
}
