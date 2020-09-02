import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task which has a task_info, a date and a time.
 */
public class Event extends Task {
    protected LocalDate date;
    protected String time;

    /**
     * Constructs a new Event instance.
     *
     * @param task_info Description of the task.
     * @param date Date when the task is due.
     * @param time Time when the task is due.
     */
    public Event(String task_info,LocalDate date, String time) {
        super(task_info);
        this.date = date;
        this.time = time;
    }

    /**
     * Overrides the Task's toString method
     * and gives details of the Event instance
     * @return The String that represents the event's details.
     */
    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " at %s %s", date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), time);
    }
}