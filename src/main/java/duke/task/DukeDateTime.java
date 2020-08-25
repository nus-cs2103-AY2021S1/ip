package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a wrapper class over {@link LocalDateTime} such that some objects may not contain time.
 */

public class DukeDateTime {
    private LocalDateTime dateTime;
    private boolean hasTime;

    public DukeDateTime(LocalDateTime dateTime, boolean hasTime){
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    @Override
    public String toString(){
        return hasTime
                ? dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"))
                : dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
