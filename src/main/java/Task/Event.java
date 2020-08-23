package Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        String icon;
        if (this.completed) {
            icon = "[" + "\u2713" + "]";
        } else {
            icon = "[" + "\u2718" + "]";
        }
        return "[E] " + icon + " " + this.description + " (at: " + this.at + ")";
    }
}
