/**
 * Represents a list command for listing all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to list all current tasks.
     * @param tasks list of tasks.
     * @param ui user interface to display listing message.
     * @param storage file storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListMessage(tasks.getTasksList());
    }
}
