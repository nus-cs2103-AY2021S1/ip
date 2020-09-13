import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A Event task that comprises a task description, date and whether it is done.
 */

public class Event extends Task {

    protected LocalDate time;

    /**
     * Create a Event task.
     * @param description of task.
     * @param isDone done status of task.
     * @param time of task.
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns the Event date.
     * @return Event date.
     */
    public String getTime(){

        return time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    /**
     * Returns the String description for an event.
     * @return String description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}
