public class Event extends Task {

    protected String date;

    public Event (String description, String date) {
        super(description);
        this.date = date;
    }

    public Event (String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    @Override
    public String toStoredTextString() {
        return "E | " + super.toStoredTextString() + " | " + date;
    }
}