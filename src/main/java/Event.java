public class Event extends Task {

    protected String by;

    public Event(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.toString() + "(at: " + by + ")";
    }
}