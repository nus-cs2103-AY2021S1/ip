public class ByeCommand extends Command {
    public ByeCommand() {
        this.exit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbye();
    }
}
