package duke.task;

/**
 * Represents a event task.
 * @author Tee Kok Siang
 */
public class Event extends Task {
    /** Keyword for extracting the event time */
    public static final String AT_KEYWORD = "/at";
    /** Position for the keyword */
    public static final int INVALID_AT_POSITION = 1;
    private final String at;

    /**
     * Constructs an Event object.
     *
     * @param description Task description.
     * @param at Task time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns formatted event task information.
     * It will be used to write into the file.
     * @return Formatted event task information.
     */
    @Override
    public String toFileString() {
        String done = super.isDone ? "1" : "0";
        return "E | " + done + " | " + priority.toString() + " | " + super.description + " | " +  at + " | " + priority.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ") [" + priority.toString() + "]";
    }
}