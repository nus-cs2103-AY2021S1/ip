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
    public void execute(TaskList taskList) {
        taskList.showList();
        System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.getTaskLength()) +
                TextUi.divider);
    }
}