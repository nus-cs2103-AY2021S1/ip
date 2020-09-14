package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    protected LocalDateTime timeOfEvent;

    /**
     * Creates an Event with the specified description and timeOfEvent, which is marked incomplete by default.
     * Used when creating a new Event in the programme.
     * @param description Description of the Event.
     * @param timeOfEvent Time at which the Event takes place.
     */
    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = LocalDateTime.parse(timeOfEvent, dateTimeInputFormatter);
    }

    /**
     * Creates an Event with the specified values for isDone and timeOfEvent.
     * Used when loading tasks from file.
     * @param description Description of the Event.
     * @param isDone Completion status of the Event.
     * @param timeOfEvent Time at which the Event takes place.
     */
    public Event(String description, boolean isDone, String timeOfEvent) {
        super(description, isDone);
        this.timeOfEvent = LocalDateTime.parse(timeOfEvent);
    }

    /**
     * Returns an array of Strings representing the state of the task, to be passed to Storage to
     * be formatted and written to a file.
     * @return Array of Strings representing the current state of the Task.
     */
    @Override
    public String[] serialize() {
        String[] output = new String[4];
        output[0] = this.isDone
                ? "1"
                : "0";
        output[1] = "Event";
        output[2] = description;
        output[3] = timeOfEvent.toString();

        return output;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent.format(dateTimeOutputFormatter) + ")";
    }
}
