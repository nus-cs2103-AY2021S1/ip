package duke.task;

import java.time.LocalDate;

/**
 * An abstract class TimedTask. A <code>TimedTask</code> object contains a description,
 * keeps track of whether it has been completed, stores a type as well as time.
 */
public abstract class TimedTask extends Task {
    // protected static final String TIME_FORMAT = "d MMM yyyy";

    protected LocalDate date;

    /**
     * Constructor for TimedTask.
     */
    public TimedTask(String description, LocalDate when) {
        super(description);
        this.date = when;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + description + " - "
                + date;
    }

    @Override
    public String toFile() {
        return this.getTypeIcon() + this.getDoneStatus() + " " + description + " - "
                + date;
    }

    /**
     * Snooze this task. Currently postpones the task by one day.
     */
    protected abstract void snooze();
}
