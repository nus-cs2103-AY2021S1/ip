package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime time;

    public Event(String name, LocalDateTime time, boolean status) {
        super(name, status);
        this.time = time;
    }

    /**
     * Returns what the current task is and its status.
     * @return String representing the task.
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: "
                + this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy kkmm")) + ")";
    }

    /**
     * Gets the deadline of the task.
     * @return DateTime object that represents the deadline.
     */
    @Override
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns the type of the task.
     * @return String that represents the type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }
}
