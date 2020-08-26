import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tasks that start at a specific time and ends at a specific time.
 */

public class Event extends Task{
    private final Date time;

    public Event(String description, boolean isDone, Date time) {
        super(description, isDone);
        this.time = time;
    }

    public Date getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[E][%s] %s (at: %s)", box, this.description,
                new SimpleDateFormat("y-M-d").format(this.time));
    }
}
