package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an event.
 */
public class Event extends Task {
    protected LocalDateTime time;

    /**
     * Creates an Event object.
     *
     * @param description The description of the event.
     * @param time        The time of the event.
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns the message to be saved in the hard disk.
     *
     * @return The string representation of the task in the local file.
     */
    @Override
    public String writeMessage() {
        String done = "";
        if (this.isDone) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("E | %s | %s | %s", done, this.description, this.time);
    }

    /**
     * Returns the string representation of the event to the users when Duke receives list command.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String str = " (at: ";
        str += time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[E][" + (this.isDone ? "✓" : "✗") + "] " + this.description + str + ")";
    }
}
