package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a wrapper class over {@link LocalDateTime} such that some objects may not contain time.
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

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
}
