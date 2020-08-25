public class InvalidCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage("Sorry I do not know what that means :(");
    }
}
