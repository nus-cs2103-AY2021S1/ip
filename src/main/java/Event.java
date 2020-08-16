public class Event extends Task {
    protected String date;

    public Event(String event, String date) {
        super(event);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
