/**
 * Encapsulates a sort command.
 */
public class SortCommand extends Command {
    
    /**
     * Sorts the tasks by date and displays the task list in chronological order.
     * @param tasks the task list with tasks to be sorted.
     * @param ui the ui used to display the sorted task list.
     * @param storage the storage used to store the task list.
     * @return a string representation of the task list in sorted order.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTasksByDate();
        return ui.showSortedList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
