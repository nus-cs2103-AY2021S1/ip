package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represents the EventTask object. This task object is used to represent an event happening at a certain date.
 */
public class EventTask extends DateTask {
    /**
     * Constructs a new EventTask object with the specified task name and date.
     * @param taskName The task's name
     * @param date The event's date
     */
    public EventTask(String taskName, LocalDateTime date) {
        super(taskName, date);
    }

    /**
     * Returns the string representation of the EventTask.
     * EventTask is represented as "E" in the front and it will also shows
     * the date in the format of MMM dd yyyy HH:mm e.g. Jan 01 2020 18:00.
     * @return A string representation of EventTask
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
