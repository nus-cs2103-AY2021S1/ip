public class Event extends Task {

    protected final String symbol = "[E]";
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return (symbol + super.toString() + " (at: " + by + ")");
    }
}
