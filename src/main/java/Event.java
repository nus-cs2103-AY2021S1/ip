public class Event extends Task{
    String eventDate;

    public Event(String eventName, String eventDate) {
        super(eventName);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + eventDate + ")";
    }
}
