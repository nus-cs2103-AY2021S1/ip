package duke.commands;

import java.time.LocalDate;

import duke.data.task.Deadline;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for the logic of adding a deadline.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a deadline with a description and due date.\n"
            + "Example: " + COMMAND_WORD + " CS2103T iP /by 2020-09-17\n";
    private final Deadline deadline;

    /**
     * Constructor initialising the deadline and description.
     * @param description Name of the deadline.
     * @param localDate The date the deadline is due.
     */
    public DeadlineCommand(String description, LocalDate localDate) {
        this.description = description;
        deadline = new Deadline(description, localDate);
    }

    /**
     * Adds deadline to task list and append it to the data file.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult with a message noting that the
     * task has been added.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.addTask(deadline);
        storage.save(deadline);
        return new CommandResult("Added: " + deadline);
    }
}
