public class Event extends Task{
    private final String by;

    protected Event(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
