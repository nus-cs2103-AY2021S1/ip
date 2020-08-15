public class Event extends Task {
    String timing;

    Event(String label, String timing) {
        super(label);
        // Remove the "at"
        this.timing = timing.substring(3);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }
}
