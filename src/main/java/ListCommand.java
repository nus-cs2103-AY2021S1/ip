/**
 * Encapsulates a list command.
 */
public class ListCommand extends Command {
    /**
     * Displays all the tasks in the taskList.
     * @param tasks the task list with tasks to be displayed.
     * @param ui the ui used to display all the tasks.
     * @param storage the storage used to store the task list.
     * @return a string representation of all the tasks in the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
