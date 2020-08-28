/**
 * handles the "find" commands
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public FindCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    /**
     * shows the entire task list
     *
     * @param taskList
     */
    public void execute(TaskList taskList) {
        String keyWord = this.getTaskName();
        TaskList keyWordTaskList = taskList.getTasksWithKeyWords(keyWord);
        TextUi.printMessage("Here are the matching tasks in your list:\n" + keyWordTaskList.toString());
        TextUi.printTaskSummary(keyWordTaskList.getTaskLength());
    }
}
