package botbot.tasks;

import java.time.LocalDateTime;

import botbot.BotbotDateTimeFormatter;

/**
 * Represents a deadline with a description, deadline and completion status.
 */
public class Deadline extends Task {
    public static final char TYPE_CODE = 'D';
    public static final String COMMAND_FORMAT = "deadline <description> /by <D-M-YYYY HHmm> (eg. 17-3-2020 0945 "
            + "or 3-4-2020 with no time specified)";

    /**
     * Creates a deadline.
     *
     * @param description Description of deadline.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(TYPE_CODE, description, null, by);
    }

    /**
     * Creates a deadline.
     *
     * @param description Description of deadline.
     * @param status Completion status of deadline.
     * @param by Deadline of task.
     */
    public Deadline(String description, TaskStatus status, String by) {
        super(TYPE_CODE, description, null, LocalDateTime.parse(by), status);
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (by: %s)", getType(), getStatusIcon(), getDescription(),
                BotbotDateTimeFormatter.convertDateTimeToStr(getBy()));
    }
}
