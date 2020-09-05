package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles addition of deadline-based Tasks.
 */
public class AddDeadlineCommand extends Command {
    /** duke.Command details in the form [TYPE, INFORMATION] */
    private final String[] instructions;

    /**
     * Constructor for duke.AddDeadlineCommand.
     * @param instructions Contains description and deadline.
     */
    public AddDeadlineCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the AddDeadline duke.Command, adding a new duke.Task of type duke.Deadline with description and date.
     * @param tasks duke.TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] deadlineInfo = instructions[1].split(" /by ", 2); // deadlineInfo = [name, deadline & tags]

        if (deadlineInfo.length < 2) {
            return ui.conditionError(Constants.TaskTypes.DEADLINE); // User provided incomplete information.
        }

        try {
            String deadline = deadlineInfo[1];
            Task deadlineTask;

            if (deadlineInfo[1].contains(" /tags ")) {
                String[] tags;
                String[] deadlineAndTags = deadlineInfo[1].split(" /tags ");
                deadline = deadlineAndTags[0];
                tags = deadlineAndTags[1].split(",");
                // cleanup whitespace
                for (int i = 0; i < tags.length; i++) {
                    tags[i] = tags[i].strip();
                }

                deadlineTask = new Deadline(deadlineInfo[0], LocalDate.parse(deadline), tags);
            } else {
                deadlineTask = new Deadline(deadlineInfo[0], LocalDate.parse(deadline));
            }

            return tasks.addTask(deadlineTask) + "\n" + storage.save(tasks);
        } catch (DateTimeParseException e) {
            return ui.invalidDateError();
        }
    }
}
