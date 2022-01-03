package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Deadline related DukeTaskWithTime.
 * Apart from fields defined in <code>DukeTask</code>,
 * <code>DeadlineTask</code> contains an additional <code>LocalDateTime</code>
 * to store information about the date and time of the deadline.
 */
public class DeadlineTask extends DukeTaskWithTime {

    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDateTimeString());
    }
}
