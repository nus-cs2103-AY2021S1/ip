public class ByeCommand implements Command{

    // constructor
    public ByeCommand() { }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.goodbye();
        ui.showLine();
    }

    public boolean isExit() {
        return true;
    }
}
