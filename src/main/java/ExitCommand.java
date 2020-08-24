public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }
}
