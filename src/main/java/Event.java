public class Event extends Task {

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}