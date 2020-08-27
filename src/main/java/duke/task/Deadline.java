package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tasks that need to be done before a specific time
 */
public class Deadline extends Task {
    protected final Date doBy;

    public Deadline(String description, boolean isDone, Date doBy) {
        super(description, isDone);
        this.doBy = doBy;
    }

    public String getDoByStr() {
        return new SimpleDateFormat("y-M-d").format(this.doBy);
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[D][%s] %s (by: %s)", box, this.description,
                new SimpleDateFormat("MMM d yyyy").format(this.doBy));
    }
}
