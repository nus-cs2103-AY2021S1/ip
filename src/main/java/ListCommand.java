public class ListCommand extends Command {
    /**
     * Displays all the tasks in the taskList.
     * @param tasks the current TaskList.
     * @param ui the current Ui.
     * @param storage the current Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
