import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles addition of deadline-based Tasks.
 */
public class AddDeadlineCommand extends Command {
    /** Command details */
    private final String[] instructions;

    /**
     * Constructor for AddDeadlineCommand.
     * @param instructions Contains description and deadline.
     */
    public AddDeadlineCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the AddDeadline Command, adding a new Task of type Deadline with description and date.
     * @param tasks TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] deadlineInfo = instructions[1].split(" /by ", 2); // [name, deadline]
        if (deadlineInfo.length < 2) {
            ui.conditionError(Constants.TaskTypes.DEADLINE);
            return;
        }
        try {
            Task deadline = new Deadline(deadlineInfo[0], LocalDate.parse(deadlineInfo[1]));
            tasks.addTask(deadline);
            storage.save(tasks);
        } catch (DateTimeParseException e) {
            ui.invalidDateError();
        }
    }
}