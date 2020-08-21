public class Event extends Task {
    private final String atTime;

    public Event(String description, String atTime) {
        super(description);
        this.atTime = atTime;
    }

    @Override
    public String output() {
        return "E" + super.output() + " | At: " + atTime + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atTime + ")";
    }
}
