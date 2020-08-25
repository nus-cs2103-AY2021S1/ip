/**
 * Represents a command to find all the tasks with a particular keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initializes the command with the keyword.
     * @param keyword The keyword to be used to filter the tasks that have a matching description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters the task list with the keyword and show it to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksFound(tasks.getListOfTasks(this.keyword));
    }
}
