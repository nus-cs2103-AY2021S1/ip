public class ExitCommand extends Command {
    private CommandType commandType;

    public ExitCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}