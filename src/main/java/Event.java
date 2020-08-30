import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task called Event.
 * An Event has a attribute "eventDateAsString" or "eventDateAsDate", which is the time the deadline is due.
 * The attribute eventDate can be represented by a String, or a date object.
 */
public class Event extends Task {
    protected String eventDateAsString;
    protected LocalDate eventDateAsDate;

    Event(String taskDescription, String date) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
        this.eventDateAsString = date;
        this.eventDateAsDate = null;
    }

    Event(String taskDescription, LocalDate date) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
        this.eventDateAsString = null;
        this.eventDateAsDate = date;
    }

    /**
     * Return the event date of the event.
     *
     * @return Event date of event.
     */
    public String getDate() {
        if (eventDateAsString != null) {
            return eventDateAsString;
        } else {
            return eventDateAsDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Return the type of Task.
     *
     * @return Type of task.
     */
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        if (eventDateAsDate != null) {
            return "[E]" + super.toString() + " (at: " + eventDateAsDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + eventDateAsString + ")";
        }
    }
}
