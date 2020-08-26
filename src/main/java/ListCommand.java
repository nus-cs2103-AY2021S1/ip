/**
 * Represents a list command for listing all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to list all current tasks.
     * @param tasks list of tasks
     * @param ui user interface to display deleted message
     * @param storage file storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListMessage();
        tasks.listTasks();
    }
}
