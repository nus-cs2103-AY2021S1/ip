package tasks;

import utils.ResourceHandler;
import java.text.MessageFormat;

/**
 * A specialised {@code Task} that starts and ends at a specific time.
 */
public class Event extends Task {
    private final String dateTime;

    /**
     * Constructs a new uncompleted {@code Event} object.
     *
     * @param name the name of the {@code Event}.
     * @param dateTime a string representing the date and time of the {@code Event}.
     */
    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a new uncompleted {@code Event} object.
     *
     * @param name the name of the {@code Event}.
     * @param dateTime a string representing the date and time of the {@code Event}.
     * @param isDone whether the {@code Event} has been completed.
     */
    public Event(String name, String dateTime, boolean isDone) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Marks the {@code Event} as done.
     *
     * @return a new completed {@code Event} object with the same {@code name} as this {@code Event}.
     */
    @Override
    public Event markAsDone() {
        return new Event(name, dateTime, true);
    }

    /**
     * Returns a string representation of this {@code Event} object.
     *
     * @return a string representation of the {@code Event}.
     */
    @Override
    public String toString() {
        return MessageFormat.format(ResourceHandler.getString("event.toString"), super.toString(), dateTime);
    }
}
