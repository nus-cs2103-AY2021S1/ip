public class ExitCommand extends Command {

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.updateTasks(taskList);
        return ui.showGoodbye();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
