public class Event extends Task{

    private String eventDate;

    public Event(String eventName, String eventDate) {
        super(eventName);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E" + " | " + super.getSaveFormat() + " | " + eventDate;
    }
}
