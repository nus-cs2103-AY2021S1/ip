public class Event extends Task {
    protected String date;

    public Event(String event, String date) {
        super(event);
        this.date = date;
    }

    public Event(String task, boolean isDone, String date) {
        super(task, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
