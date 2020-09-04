package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.CommonString;

/**
 * Represents a generic DukeTask containing <code>LocalDateTime</code> variable.
 * It contains a <code>String</code> description describing the task,
 * a <code>boolean</code> isDone to denote if the task is completed.
 */
public abstract class DukeTaskWithTime extends DukeTask {
    private final LocalDateTime datetime;
    public DukeTaskWithTime(String description, LocalDateTime datetime) {
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
}
