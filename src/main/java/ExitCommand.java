public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
        storage.saveTasks(manager.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}