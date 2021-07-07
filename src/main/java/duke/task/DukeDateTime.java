package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a wrapper class over {@link LocalDateTime}.
 */
public class DukeDateTime {
    private LocalDateTime dateTime;

    /**
     * Creates a DukeDateTime object with the inputted datetime and also a boolean value to indicate whether it
     * contains time or not.
     *
     * @param dateTime The LocalDateTime involved.
     */
    public DukeDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a {@code LocalDateTime} object containing the {@code DukeDateTime} datetime.
     *
     * @return the datetime of the {@code DukeDateTime} object
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns the string representation of this {@code DukeDateTime} object.
     *
     * @return the string representation of this {@code DukeDateTime} object.
     */
    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
}
