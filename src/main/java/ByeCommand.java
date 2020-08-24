public class ByeCommand implements Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
        return false;
    }
}
