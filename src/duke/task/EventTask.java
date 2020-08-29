package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represents the EventTask object. This task object
 * is used to represent an event happening at a certain date.
 */
public class EventTask extends Task {
    private LocalDateTime date;

    /**
     * Constructs a new EventTask object with the specified task name and date.
     * When a new Task object is created, the status is set to ongoing by default.
     *
     * @param taskName The task's name
     * @param date The event's date
     */
    public EventTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Gets the eventTask's date.
     *
     * @return The event's date
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Gets the string representation of the date in the format of
     * yyyy-MM-dd HHmm e.g. 2020-01-01 1400 represents Jan 01 2019 2 pm.
     *
     * @return A string representation of the date
     */
    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the string representation of the EventTask.
     * EventTask is represented as "E" in the front and it will also shows
     * the date in the format of MMM dd yyyy HH:mm e.g. Jan 01 2020 18:00.
     *
     * @return A string representation of EventTask
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
