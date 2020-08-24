package duke;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a timed task containing a description and date-time.
 * Inherits from abstract class Task.
 */
public abstract class TimedTask extends Task {
    protected LocalDateTime datetime;
    protected static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-M-yyyy k:mm");
    protected static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");

    /**
     * (Abstract) constructor to be called by concrete subclasses.
     *
     * @param description describes the task.
     * @param datetime contains date and time info.
     */
    protected TimedTask(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.parse(datetime, TimedTask.inputFormatter);
    }

    /**
     * Returns a string representing date and time.
     * Format is "dd MMM yyyy hh.mma".
     *
     * @return date and time string.
     */
    protected String datetimeString() {
        return this.datetime.format(TimedTask.printFormatter);
    }

    /**
     * Returns a LocalDate object containing date info.
     *
     * @return LocalDate date.
     */
    protected LocalDate getDate() {
        return this.datetime.toLocalDate();
    }

}
