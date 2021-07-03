package botbot.commands;

import java.time.LocalDateTime;

import botbot.tasks.Deadline;

/**
 * Adds a deadline to the task list.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_KEYWORD = "deadline";

    /**
     * Creates a deadline command to add a deadline.
     *
     * @param description Description of deadline.
     * @param by Deadline of task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        super(new Deadline(description, by));
    }
}
