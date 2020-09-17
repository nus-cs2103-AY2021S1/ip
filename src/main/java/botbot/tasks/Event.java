package botbot.tasks;

import java.time.LocalDateTime;

import botbot.utils.BotbotDateTimeFormatter;

/**
 * Represents an event with a description, time and completion status.
 */
public class Event extends Task {
    public static final char TYPE_CODE = 'E';
    public static final String COMMAND_FORMAT = "event DESCRIPTION /at DATE (D-M-YYYY) [TIME (HHmm)] (eg. "
            + "17-3-2020 0945 or 3-4-2020 with no time specified)";

    /**
     * Creates an event.
     *
     * @param description Description of event.
     * @param at Time of event.
     */
    public Event(String description, LocalDateTime at) {
        super(TYPE_CODE, description, at, null);
    }

    /**
     * Creates an event.
     *
     * @param description Description of event.
     * @param status Completion status of event.
     * @param at Time of event.
     */
    public Event(String description, TaskStatus status, String at) {
        super(TYPE_CODE, description, LocalDateTime.parse(at), null, status);
    }
    
    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (at: %s)", getType(), getStatusIcon(), getDescription(),
                BotbotDateTimeFormatter.convertDateTimeToStr(getAt()));
    }
}
