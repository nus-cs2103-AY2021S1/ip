public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.close();
    }

    @Override
    public boolean isInProgram() {
        return false;
    }
}
