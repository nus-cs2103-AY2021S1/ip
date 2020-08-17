public class Event extends Task {

    // Attributes
    protected String dateTime;

    // Constructor
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
