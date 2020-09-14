package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate eventTime;
    private LocalDateTime time;

    /**
     * Initializes an event task.
     *
     * @param description Description of the task.
     * @param time        Date/Time that the event takes place.
     * @param hasTime     Whether there is a specified time for the event.
     * @param isDone      Whether the event is done or not.
     * @param priority    Priority level of the event.
     */
    public Event(
            String description, String time, boolean hasTime, boolean isDone, String priority) {
        super(description, isDone, priority);
        if (!hasTime) {
            this.eventTime = LocalDate.parse(time);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.time = LocalDateTime.parse(time, formatter);
        }
    }

    /**
     * Gets the string representation of the event task to be stored in the data file.
     *
     * @return String representation of the event task in the data file.
     */
    @Override
    public String getStorageString() {
        return "E | " + this.getStatusIcon() + " | " + this.description
            + " | " + this.time + " | " + this.priority.toString() + "\n";
    }

    @Override
    public String toString() {
        String timeFormat;
        if (eventTime != null) {
            timeFormat = eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            timeFormat = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            int hour = time.getHour();
            int min = time.getMinute();
            timeFormat += " " + hour + ":" + (min < 10 ? "0" : "") + min;

        }
        return "[E][" + this.getStatusIcon() + "] "
                + this.description + " (at: " + timeFormat + ")"
                + " (Priority: " + this.priority.toString() + ")";
    }
}
