public class UnknownCommand implements Command{

    protected final String command;

    public UnknownCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.unknownCommand(command);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
