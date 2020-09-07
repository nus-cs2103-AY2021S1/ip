package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.utils.Datetime;

/**
 * Represents Event objects.
 * Inherits from the abstract <code>Task</code> class.
 */
public class Event extends Task {
    public static final String EVENT_SYMBOL = "E";
    public static final String TIME_FORMAT_INPUT = "HHmm";
    public static final String TIME_FORMAT_OUTPUT = "HH:mm";
    public static final String EVENT_BREAK = "/at";

    private final Datetime time;
    /**
     * Constructor method.
     *
     * @param description the description of the <code>Event</code>.
     * @param isCompleted the completion status of the <code>Event</code>.
     * @param time the <code>LocalDateTime</code> the <code>Event</code> task is to be completed by.
     */
    public Event(String description, boolean isCompleted, LocalDateTime time) {
        super(description, isCompleted);
        this.time = new Datetime(time, TIME_FORMAT_INPUT, TIME_FORMAT_OUTPUT);
    }

    /**
     * Creates an <code>Event</code> that is not completed.
     *
     * @param description the description of the <code>Event</code>.
     * @param time the <code>LocalDateTime</code> the <code>Event</code> task is to be completed by.
     * @return an uncompleted <code>Event</code>.
     */
    public static Event createEvent(String description, LocalDateTime time) {
        return new Event(description, false, time);
    }

    /**
     * Converts the <code>Event</code> to a <code>String</code>.
     *
     * @return a <code>String</code> representing the <code>Event</code>.
     */
    @Override
    public String toString() {
        String stringTime = time.getOutputDatetimeString();
        String atTime = String.format("(at: %s)", stringTime);
        return "[" + EVENT_SYMBOL + "]" + toStringSuffix() + " " + atTime;
    }

    /**
     * Checks if the other object is equivalent to the <code>Event</code>.
     * The description, completion status and time must be equal.
     *
     * @param other the object to be compared to.
     * @return <code>true</code> if both objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            boolean isTimeEqual = this.getTaskDatetime().equals(otherEvent.getTaskDatetime());
            return this.isEqual(otherEvent) && isTimeEqual;
        }
        return false;
    }

    /**
     * Retrieves the symbol of <code>Event</code>.
     *
     * @return the <code>String</code> symbol of <code>Event</code>.
     */
    @Override
    public String getTaskSymbol() {
        return EVENT_SYMBOL;
    }

    /**
     * Gets the datetime <code>String</code> of <code>Event</code>.
     *
     * @return an <code>Optional</code> containing the formatted <code>String</code>.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        String stringTime = time.getOutputDatetimeString();
        return Optional.of(stringTime);
    }
}
