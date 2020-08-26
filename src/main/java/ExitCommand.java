public class ExitCommand implements Command {

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
