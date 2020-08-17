public class Event extends Task {
    protected String date;

    public Event(String name, String date) {
        super(name);
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
