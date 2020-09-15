/**
 * handles the "list" commands
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public ListCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    /**
     * shows the entire task list
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        return TextUi.printMessage("Here are the tasks in your list:\n"
                + taskList.toString()) + TextUi.printTaskSummary(taskList.getTaskLength());
    }
}
