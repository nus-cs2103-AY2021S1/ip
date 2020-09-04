package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */

public class Event extends Task implements TimeBased {
    private final DukeDateTime dateTime;

    public Event(String content, DukeDateTime dateTime) {
        super(content);
        this.dateTime = dateTime;
    }

    public Event(String content, DukeDateTime dateTime, boolean isDone) {
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
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, content, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
