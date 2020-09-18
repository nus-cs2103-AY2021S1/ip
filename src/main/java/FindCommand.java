/**
 * Encapsulates a find command with an input.
 */
public class FindCommand extends Command {
    /**
     * Represents the input string used to find certain tasks.
     */
    String input;

    /**
     * Instantiates a FindCommand object.
     * @param input the input string used to find certain tasks.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the tasks in the task list which matches the input string.
     * @param tasks the task list to find the tasks from.
     * @param ui the ui used to display the tasks which match the input.
     * @param storage the storage used to store the task list.
     * @return a string representation of the tasks which match the input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String matches = tasks.findTask(input);
        return ui.showTasksFound(matches);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
