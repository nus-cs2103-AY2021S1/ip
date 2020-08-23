public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksToString = tasks.tasksToString();
        ui.printMessage(tasksToString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
