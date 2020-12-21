package duke;

/**
 * Represents a list command for tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes listing of tasks.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @return Text when ListCommand is executed.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        return ui.displayTaskList(taskList);
    }
}
