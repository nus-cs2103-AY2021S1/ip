/**
 * Tasks that start at a specific time and ends at a specific time.
 */

public class Event extends Task{
    private final String time;

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[E][%s] %s (at: %s)", box, this.description, this.time);
    }
}
