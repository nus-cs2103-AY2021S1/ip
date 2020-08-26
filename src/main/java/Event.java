public class Event extends Task {
    protected String at;

    Event(String task, String date) {
        super(task);
        this.at = date;
    }

    public String getDate() {
        return at;
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
