public class Event extends Task {
    private String timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return String.format("[E]%s (at: %s)", super.toString(), this.timePeriod);
    }
}
