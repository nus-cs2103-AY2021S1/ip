package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that needs to be done at a certain date and time. An
 * <code>Event</code> object is represented as a Task with a LocalDateTime.
 */
public class Event extends TimedTask {

    /**
     * Constructs an <code>Event</code> object with a description and a LocalDateTime.
     * This Event is marked as undone.
     *
     * @param description Describes what to do.
     * @param at String representation of date and time.
     */
    public Event(String description, String at) {
        super(description, at);
        this.taskType = TaskType.EVENT;
    }

    /**
     * Constructs an <code>Event</code> object with a description,
     * a boolean to indicate if the Event is done and a LocalDateTime.
     *
     * @param description Describes what to do.
     * @param isDone Indicates if the Event is done.
     * @param at String representation of date and time.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone, at);
        this.taskType = TaskType.EVENT;
    }

    /**
     * Returns a description of the Event to be stored in a file.
     *
     * @return A description of the Event.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | "
                + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns a description of the Event.
     *
     * @return A description of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")) + ")";
    }
}
