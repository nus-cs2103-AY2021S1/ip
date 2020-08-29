package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.CommonString;

/**
 * Represents a Deadline related DukeTask.
 * Apart from fields defined in <code>DukeTask</code>,
 * <code>DeadlineTask</code> contains an additional <code>LocalDateTime</code>
 * to store information about the date and time of the deadline.
 */
public class DeadlineTask extends DukeTask {
    private final LocalDateTime datetime;

    public DeadlineTask(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Returns a <code>String</code> representing the Date and Time of the task.
     * The <code>String</code> is formatted by the <code>DateTimeFormatter</code> defined.
     *
     * @return String DateTime.
     */
    public String getDatetime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
        return datetime.format(df);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDatetime());
    }
}
