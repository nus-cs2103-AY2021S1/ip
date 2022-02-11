/**
 * Class to run the list command.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, causing Duke to print the tasks it is currently tracking.
     *  @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String result = ui.showLine() + "\n" + ui.printTasks(taskList.TASKS) + ui.showLine();
        return result;
    }

    /**
     * Returns true if a bye command is called.
     * Returns False otherwise.
     *
     * @return boolean indicating whether Duke is to stop running.
     */
    public boolean isExit() {
        return false;
    }
}
