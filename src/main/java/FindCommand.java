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
    public String execute(TaskList taskList) {
        String keyWord = this.getTaskName();
        TaskList keyWordTaskList = taskList.getTasksWithKeyWords(keyWord);
        return TextUi.printMessage("Here are the matching tasks in your list:\n" + keyWordTaskList.toString())
                + "\n" + TextUi.printTaskSummary(keyWordTaskList.getTaskLength());
    }
}
