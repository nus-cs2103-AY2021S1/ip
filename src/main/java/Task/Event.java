package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is the type of Task where a scheduled event is to take place at a specific time.
 * @author Joshua
 */
public class Event extends Task {

    /**
     * NEW_DATETIME_FORMAT is the date time format that will be displayed to the user.
     * SAVE_READ_DATETIME_FORMAT is the date time format that is stored internally in Duke.
     */
    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final static DateTimeFormatter NEW_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mma");

    /**
     * Creates the Event with the given task description.
     * @param taskDescription the description given by the user for this task
     */
    public Event(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Sets the time for the event
     * @param givenDate the time given by the user for this event.
     */
    public void setTime(LocalDateTime givenDate) {
        date = givenDate;
    }

    /**
     * Formats the Event Task into the format it is stored as.
     * @return the formatted Event task.
     */
    @Override
    public String saveFormat() {
        String base = "[E] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "at:" + date.format(SAVE_READ_DATETIME_FORMAT);
        return base;
    }

    /**
     * Returns the event to the ui to be displayed to the user.
     * @return a String that contains the event time and date in the display format.
     */
    @Override
    public String toString() {
        String base = "[E] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "(at:" + date.format(NEW_DATETIME_FORMAT) + ")";
        return base;
    }
}
