package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline object with a deadline timing.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a deadline object.
     *
     * @param description title of the deadline.
     * @param by deadline timing.
     * @param isDone status.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + by.format(format) + ")";
    }

    /**
     * Represents the string written into data.txt.
     *
     * @return A string written into the data.txt.
     */
    @Override
    public String toWrite() {
        return "D | " + (this.isDone ? '1' : '0') + " | " + this.taskDescription
                + "| " + this.by.toString().replace('T', ' ');
    }
}
