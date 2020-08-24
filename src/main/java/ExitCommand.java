public class ExitCommand implements Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage store) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
