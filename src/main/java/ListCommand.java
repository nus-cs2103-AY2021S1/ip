/**
 * Encapsulates a ListCommand object.
 */
public class ListCommand extends Command {

    /**
     * Displays all the tasks in the task list.
     *
     * @param taskList task list containing the tasks.
     * @param ui ui used to show the list of tasks.
     * @param storage storage used to store the taskList.
     * @return a string representation of the list of tasks.
     */
    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList.toString());
    }
}
