package botbot.tasks;

import java.time.LocalDateTime;

import botbot.BotbotDateTimeFormatter;

/**
 * Represents an event with a description, time and completion status.
 */
public class Event extends Task {
    public static final char TYPE_CODE = 'E';
    public static final String COMMAND_FORMAT = "event <description> /at <D-M-YYYY HHmm> (eg. 17-3-2020 0945 "
            + "or 3-4-2020 with no time specified)";
    private LocalDateTime at;

    /**
     * Creates an event.
     *
     * @param description Description of event.
     * @param at Time of event.
     */
    public Event(String description, LocalDateTime at) {
        super(TYPE_CODE, description);
        this.at = at;
    }

    /**
     * Creates an event.
     *
     * @param description Description of event.
     * @param status Completion status of event.
     * @param at Time of event.
     */
    public Event(String description, TaskStatus status, String at) {
        super(TYPE_CODE, description, status);
        this.at = LocalDateTime.parse(at);
    }

    /**
     * Returns the time of the event.
     *
     * @return Time of event.
     */
    @Override
    public String getAt() {
        return at.toString();
    }

    /**
     * Returns the deadline of the event.
     *
     * @return Null.
     */
    @Override
    public String getBy() {
        return null;
    }
    
    @Override
    public String toString() {
        return String.format("[%c] [%s] %s (at: %s)", getType(), getStatusIcon(), getDescription(),
                BotbotDateTimeFormatter.convertDateTimeToStr(at));
    }
}
