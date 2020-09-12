/**
 * handles the "find" commands
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand
     *
     * @param keyWord
     * @throws IllegalArgumentException
     */
    public FindCommand(String keyWord) throws IllegalArgumentException {
        super(keyWord);
    }

    /**
     * shows the entire task list
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        String keyWord = this.getTaskName();
        assert keyWord != null;
        TaskList keyWordTaskList = taskList.getTasksWithKeyWords(keyWord);
        return TextUi.printMessage("Here are the matching tasks in your list:\n" + keyWordTaskList.toString());
    }
}
