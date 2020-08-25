public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getFormattedString() {
        return "E | " + (super.isDone? 1 : 0) + " | " + super.description + " | " +
                at;
    }
}
