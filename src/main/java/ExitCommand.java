public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.closeScanner();
        ui.showByeMessage();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
