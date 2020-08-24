public class ExitCommand extends Command {
    protected ExitCommand() {}


    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
