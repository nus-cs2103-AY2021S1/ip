package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task. It has a description and specified timing attribute.
 */
public class Event extends Task {

    private static final String TYPE = "E";
    private static final String TYPE_ICON = "[E]";
    protected LocalDateTime at;

    /**
     * Creates a new Event Task with a specified description and time attribute. String time
     * Object has to have proper formatting "yyyy-MM-dd HH:mm" as it will be parsed
     * as a LocalDateTime Object.
     *
     * @param description Describes the Event.
     * @param at          The specified timing of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_PATTERN));
        this.storeAs = storeNotDoneEvent(description, at);
    }

    /**
     * Creates a new Event Task with a specified done indicator, description and time
     * attribute. String time Object has to have proper formatting "yyyy-MM-dd HH:mm"
     * as it will be parsed as a LocalDateTime Object.
     *
     * @param done        Indicates whether the task has been done.
     * @param description Describes the Event.
     * @param at          The specified timing of the Event.
     */
    public Event(String done, String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_PATTERN));

        if (done.equals(DONE)) {
            this.isDone = true;
            this.storeAs = storeDoneEvent(description, at);
        }
        this.storeAs = storeNotDoneEvent(description, at);
    }


    /**
     * Returns a String representation of the Event Task.
     *
     * @return representation of the Event Task.
     */
    @Override
    public String toString() {
        return TYPE_ICON + super.toString() + showEventTime();
    }

    private String storeDoneEvent(String description, String at) {
        return TYPE + SEPARATOR + DONE + SEPARATOR + description + SEPARATOR + at;
    }

    private String storeNotDoneEvent(String description, String at) {
        return TYPE + SEPARATOR + NOT_DONE + SEPARATOR + description + SEPARATOR + at;
    }

    private String showEventTime() {
        return " (at: " + at.format(DateTimeFormatter.ofPattern(DATE_TIME_OUTPUT_PATTERN)) + ")";
    }

}
