public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listStoredTasks(taskList.getStoredTasks());
    }

    @Override
    public boolean isInProgram() {
        return true;
    }
}
