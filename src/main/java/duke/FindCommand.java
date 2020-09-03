package duke;

/**
 * Represents a find command to find tasks which match a particular search description.
 */
public class FindCommand extends Command {

    private String description;

    /**
     * Initializes a find command.
     *
     * @param description The search description to find the command.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Generates a task list with descriptions containing the search description and
     * prints each task in the list.
     *
     * @param taskList The existing task list.
     * @param ui       The UI instance which handles Duke's user interface.
     * @param storage  The existing storage for Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.processResultTaskList(taskList.generateResultTaskList(description));
    }

    /**
     * Returns if the program should continue running at the current point in time.
     * If not, the program should be exited.
     *
     * @return If the program should continue running.
     */
    @Override
    public boolean isInProgram() {
        return true;
    }
}
