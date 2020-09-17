package duke.task;

import static duke.util.DateFormatter.FORMAT_DATE_TIME;

import java.time.LocalDateTime;

/**
 * Class that simulates the event task that user has inputted.
 */
public class Event extends Task {

    /**
     * Initialize a event object the containing details of the task.
     *
     * @param description Details of the task.
     * @param eventTime LocalDateTime format of the date and time.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description, 0, eventTime);
    }

    /**
     * Initialize a event object the containing details of the task.
     *
     * @param description Details of the task.
     * @param eventTime String format of the time.
     * @param isDone Boolean value of whether a task is completed.
     * @param isReminderOn Boolean value of whether this task needs a reminder.
     */
    public Event(String description, String eventTime, boolean isDone, boolean isReminderOn) {
        super(description, isDone, isReminderOn, 0, LocalDateTime.parse(eventTime));
    }

    /**
     * Return a proper styling to be recorded into CSV.
     *
     * @return A formatted string to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("event,%s%s", getDueDate(), super.formatStyling());
    }

    /**
     * A String representation of the event object.
     *
     * @return A String representation of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDueDate().format(FORMAT_DATE_TIME) + ")";
    }
}
