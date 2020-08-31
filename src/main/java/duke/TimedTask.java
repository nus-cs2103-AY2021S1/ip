package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a timed task containing a description and date-time.
 * Inherits from abstract class Task.
 */
public abstract class TimedTask extends Task {
    protected static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy k:mm");
    protected static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");
    protected LocalDateTime dateTime;

    /**
     * (Abstract) constructor to be called by concrete subclasses.
     *
     * @param description describes the task.
     * @param datetime contains date and time info.
     */
    protected TimedTask(String description, String datetime) {
        super(description);
        this.dateTime = LocalDateTime.parse(datetime, TimedTask.INPUT_FORMAT);
    }

    /**
     * Returns a string representing date and time.
     * Format is "dd MMM yyyy hh.mma".
     *
     * @return date and time string.
     */
    protected String getDateTimeString() {
        return this.dateTime.format(TimedTask.PRINT_FORMAT);
    }

    /**
     * Returns a LocalDate object containing date info.
     *
     * @return LocalDate date.
     */
    protected LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

}
