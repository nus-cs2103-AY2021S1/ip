public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}