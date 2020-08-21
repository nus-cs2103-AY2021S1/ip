public class Event extends Task {
    String date;

    Event(String description, String date) {
        super(description);
        this.date = date;
    }

    Event(String description, String date, boolean done) {
        super(description, done);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
