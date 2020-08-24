/**
 * Class to represent a task happening at a specified date and time.
 */
public class Event extends Task {

    // Attributes
    protected String dateTime;

    // Constructor

    /**
     * Creates a new Event object.
     * @param description description of the event.
     * @param dateTime date and time of the event.
     * @throws EmptyBodyException If the description is empty.
     */
    public Event(String description, String dateTime) throws EmptyBodyException {
        super(description);
        this.dateTime = dateTime;
    }

    // String Representation
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }
}
