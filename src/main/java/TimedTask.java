/**
 * Represents a TimedTask. A <code>TimedTask</code> object contains a description,
 * keeps track of whether it has been completed, stores a type as well as time.
 */
public class TimedTask extends Task {
    protected String time;

    public TimedTask(String description, String when) {
        super(description);
        this.time = when;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + description + " - " + time;
    }
}