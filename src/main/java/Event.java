/**
 * Represents an event as a task with a date and/or time.
 */
public class Event extends Task {

    private String dateTime;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.dateTime = DateTimeHandler.parseDateTime(at);
    }

    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
