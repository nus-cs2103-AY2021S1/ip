public class Event extends Task {

    String eventDate;

    public Event(String description, String date) {
        super(description);
        this.eventDate = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate + ")";
    }
}
