public class Event extends Task {

    protected String date;

    public Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
