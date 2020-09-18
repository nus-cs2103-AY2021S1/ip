package cartona.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The TaskDate class represents the Date associated with some subclasses of Tasks.
 *
 * @author Jaya Rengam
 */
public class TaskDate {
    private LocalDateTime dateTime;

    /**
     * Creates a TaskDate from a LocalDate and a string representing the time
     *
     * @param date LocalDate representing the date of the TaskDate
     * @param time the time of the TaskDate (format HHmm)
     */
    public TaskDate(LocalDate date, String time) {
        this.dateTime = date.atTime(LocalTime
                                    .parse(time, DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * Creates a TaskDate object with the given date and time.
     *
     * @param dateTime The LocalDateTime object signifying the date and time of the TaskDate.
     */
    public TaskDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    public String getTime() {
        return this.dateTime.toLocalTime()
                            .format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
