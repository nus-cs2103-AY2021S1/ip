package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        this(description, dateTime, TaskPriority.NONE, Collections.emptyList(), false);
    }

    public Deadline(String description, LocalDateTime dateTime, TaskPriority priority, List<String> tags) {
        this(description, dateTime, priority, tags, false);
    }

    /**
     * The deadline constructor.
     *
     * @param description The description of the deadline.
     * @param dateTime The date/time the deadline is due by.
     * @param priority Priority of deadline.
     * @param tags List of tags.
     * @param isDone The boolean keeping track of whether the deadline is done.
     */
    public Deadline(String description, LocalDateTime dateTime,
                    TaskPriority priority, List<String> tags, boolean isDone) {
        super(description, priority, tags, isDone);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns a boolean indicating if the deadline is due by the given date.
     *
     * @param date A date.
     * @return true if the deadline is due by the given date.
     */
    @Override
    public boolean isDue(LocalDate date) {
        return this.dateTime.toLocalDate().equals(date);
    }

    @Override
    public String toSaveData() {
        return "D | " + super.toSaveData() + " | " + this.dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

        return "[D]" + super.toString() + " (by: " + this.dateTime.format(formatter) + ")";
    }
}
