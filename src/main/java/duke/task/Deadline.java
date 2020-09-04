package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task which needs to be done before a specific date/time.
 */

public class Deadline extends Task implements TimeBased {
    private final DukeDateTime dateTime;

    public Deadline(String content, DukeDateTime dateTime) {
        super(content);
        this.dateTime = dateTime;
    }

    public Deadline(String content, DukeDateTime dateTime, boolean isDone) {
        super(content, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public DukeDateTime getDukeDateTime() {
        return this.dateTime;
    }

    @Override
    public LocalDateTime getDateTime() {
        LocalDateTime taskDateTime = this.dateTime.getDateTime();
        return taskDateTime;
    }

    @Override
    public LocalDate getDate() {
        LocalDateTime taskLocalDateTime = this.getDateTime();
        LocalDate taskDate = taskLocalDateTime.toLocalDate();
        return taskDate;
    }

    @Override
    public String toDataFileFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, content, dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

}
