/**
 * Tasks that start at a specific time and ends at a specific time.
 */

public class Event extends Task{
    protected final String time;

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[E][%s] %s (at: %s)", box, this.description, this.time);
    }
}
