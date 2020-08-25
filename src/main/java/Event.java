public class Event extends Task {
    private final String duration;


    public Event(String name, boolean done, String duration) {
        super(name, done);
        this.duration = duration;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[E][%s] %s (at: %s)", doneSymbol, getName(), duration);
    }

    @Override
    public String toSaveString() {
        return String.format("E|%d|%s|%s", isDone() ? 1 : 0, getName(), duration);
    }
}
