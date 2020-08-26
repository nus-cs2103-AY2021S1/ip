public class Event extends Task {
    private final String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String toSaveData() {
        return "E | " + super.toSaveData() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
