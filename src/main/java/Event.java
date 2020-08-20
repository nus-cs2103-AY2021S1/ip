public class Event extends Task {
    private String timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        String symbol = this.done ? "\u2713" : "\u2717";
        return String.format("[E][%s] %s (at: %s)", symbol, this.name, this.timePeriod);
    }
}
