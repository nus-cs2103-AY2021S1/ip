package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event object with timing.
 */
public class Event extends Task {
    protected LocalDateTime time;

    /**
     * Construct an event object.
     * @param description title of the event.
     * @param time time of event happening time.
     * @param isDone status.
     */
    public Event(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(at: " + time.format(format) + ")";
    }

    /**
     * Represents the string written into data.txt.
     * @return A string written into the data.txt.
     */
    @Override
    public String toWrite() {
        return "E | " + (this.isDone ? '1' : '0')  + " | " + this.taskDescription + "| " + this.time.toString().replace('T', ' ');
    }
}
