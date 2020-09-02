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
        return ui.displayTaskList(taskList);
    }
}
