public class Event extends Task {
    protected String at;

    Event(String task, String date) {
        super(task);
        this.at = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
