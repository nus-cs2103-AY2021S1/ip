public class ExitCommand extends Command {

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.updateTasks(taskList);
        ui.showGoodbye();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
