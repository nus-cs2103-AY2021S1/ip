package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Deadline</code> object characterized by a <code>command</code> and a <code>time</code>.
 */
public class Deadline extends Task{
    /**
     * The time of a deadline.
     */
    private LocalDateTime time;

    /**
     * Creates a new <code>Deadline</code> with the given <code>command</code> and <code>time</code>.
     */
    public Deadline(String command, LocalDateTime time) {
        super(command);
        this.time = time;
    }

    /**
     * Returns the time of a deadline.
     * @return the time of this deadline.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns a string representation of a deadline.
     * @return the string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}