public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list!");
        ui.showMessage(tasks.toString());
    }

    @Override
    boolean isExit() {
        return false;
    }
}
