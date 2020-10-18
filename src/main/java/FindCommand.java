/**
 * Encapsulates a FindCommand object, contains information about the keyword that the task description
 * need to contain.
 */
public class FindCommand extends Command {

    /**
     * Represents the keyword used to search for tasks.
     */
    private final String keyword;
    
    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all the tasks in the task list that contains the keyword in their description.
     * 
     * @param taskList task list that contains the tasks to be searched.
     * @param ui ui used to show the tasks that contain the keyword.
     * @param storage storage used to store the taskList.
     * @return a string representation of the tasks that contain the keyword.
     */
    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        String matchingTasks = taskList.findTask(keyword);
        return ui.showMatchingTask(matchingTasks);
    }
}
