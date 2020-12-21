package duke;

/**
 * Represents a find command for a task.
 */
public class FindCommand extends Command {

    /** User input as a string */
    private final String userInput;

    /**
     * Constructs a FindCommand object with user input.
     * @param userInput User input as a string.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes finding tasks with keyword.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @return Text when FindCommand is executed.
     * @throws DukeException When invalid inputs are entered.
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage,
            Ui ui) throws DukeException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        return ui.displayFindings(taskList.findTasks(userInput));
    }
}
