public class FormatCommand extends Command {
    public FormatCommand() {
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showFormat();
    }
}
