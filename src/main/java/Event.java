import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an event task. Each Event instance has a name, date, and time.
 */
public class Event extends Task {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Public Constructor.
     *
     * @param name Name of event.
     * @param date Date of event.
     * @param time Time of event.
     * @throws BlankTaskException If event name is blank.
     */
    public Event(String name, LocalDate date, LocalTime time) throws BlankTaskException {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * Gets the date of the event.
     *
     * @return Date of event.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of the event.
     *
     * @return Time of event.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a String representation of the Event. Includes the task type, name, done status, date, and time (if
     * applicable).
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("EEE, d MMM yy"))
                + (time == null ? "" : ", " + time.format(DateTimeFormatter.ofPattern("h.mm a"))) + ")";
    }

    /**
     * Returns a list of attributes of the event. Includes the type, name, done status, date, and time (if applicable).
     *
     * @return List of attributes of the event.
     */
    @Override
    public String[] attributeList() {
        return new String[] { "E", getName(), date.toString(), time.toString(), String.valueOf(isDone()) };
    }

    /**
     * Compares this Event to another object. The result is true if and only if the other object is a Event with all the
     * attributes being the same as the attributes of this event.
     *
     * @param o Other object.
     * @return True if the given object represents a Event equivalent to this Event.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(date, event.date) &&
                Objects.equals(time, event.time);
    }

    /**
     * Returns a hash code for this event. The hashcode is determined from the event's name, date, and time.
     *
     * @return A hashcode for the event.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, time);
    }
}
