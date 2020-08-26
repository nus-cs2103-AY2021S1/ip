public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks.getTasksList());
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
