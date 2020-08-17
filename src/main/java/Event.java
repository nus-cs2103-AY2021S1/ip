public class Event extends Task {

    protected String timeOfEvent;

    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }
}
