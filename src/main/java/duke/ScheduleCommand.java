package duke;

/**
 * Represents a schedule command for a class.
 */
public class ScheduleCommand extends Command {

    /** User input as string */
    private final String userInput;

    /**
     * Constructs a new instance of a ScheduleCommand object with user input.
     * @param userInput User input as string.
     */
    public ScheduleCommand(String userInput) {
        this.userInput = userInput;
    }
    /**
     * Executes the displaying of scheduled tasks.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @return Scheduled tasks.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        return ui.displayScheduledTasks(taskList.findScheduledTasks(userInput));
    }
}
