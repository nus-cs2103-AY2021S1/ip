public class Event extends Task {
    private final String duration;


    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[E][%s] %s (at: %s)", doneSymbol, getName(), duration);
    }
}
