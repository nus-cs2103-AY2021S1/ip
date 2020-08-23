package Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.completed = isDone;
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

    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "E|" + completedBinary + "|" + this.description + "|" + this.at;
    }
}
