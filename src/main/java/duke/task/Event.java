package duke.task;

import duke.utils.Datetime;

import java.util.Optional;
import java.time.LocalDateTime;

/**
 * Represents Event objects.
 * Inherits from the abstract Task class.
 */
public class Event extends Task{
    private final Datetime time;

    public static final String EVENT_SYMBOL = "E";
    public static final String TIME_FORMAT_INPUT = "HHmm";
    public static final String TIME_FORMAT_OUTPUT = "hh:mm a";
    public static final String EVENT_BREAK = "/at";

    /**
     * Constructor method.
     * @param description the description of the Event.
     * @param isCompleted the completion status of the Event.
     * @param time the LocalDateTime the Event task is to be completed by.
     */
    public Event(String description, boolean isCompleted, LocalDateTime time) {
        super(description, isCompleted);
        this.time = new Datetime(time, TIME_FORMAT_INPUT, TIME_FORMAT_OUTPUT);
    }

    /**
     * Creates a Event object that is not completed.
     * @param description the description of the Event.
     * @param time the LocalDateTime the Event task is to be completed by.
     * @return an uncompleted Event object.
     */
    public static Event createEvent(String description, LocalDateTime time) {
        return new Event(description, false, time);
    }

    /**
     * Converts the Event object to a String,
     * @return a String representing the Event object.
     */
    @Override
    public String toString() {
        String stringTime = this.time.getOutputDatetimeString();
        String atTime = String.format("(at: %s)", stringTime);
        return "[" + EVENT_SYMBOL + "]" + toStringSuffix() + " " + atTime;
    }

    /**
     * Checks if the other Object is equivalent to the Event object.
     * @param other the object to be compared to.
     * @return true if both objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.isEqual(otherEvent);
        }
        return false;
    }

    /**
     * Retrieves the symbol of Event.
     * @return the String symbol of Event.
     */
    @Override
    public String getTaskSymbol() {
        return EVENT_SYMBOL;
    }

    /**
     * Gets the Datetime String of Event.
     * @return an Optional object containing the formatted String.
     */
    @Override
    public Optional<String> getTaskDatetime() {
        String stringTime = this.time.getOutputDatetimeString();
        return Optional.of(stringTime);
    }
}
