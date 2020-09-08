package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a TimedTask. A <code>TimedTask</code> object contains a description,
 * keeps track of whether it has been completed, stores a type as well as time.
 */
public abstract class TimedTask extends Task {
    protected static final String TIME_FORMAT = "d MMM yyyy";

    protected LocalDate date;

    public TimedTask(String description, LocalDate when) {
        super(description);
        this.date = when;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + description + " - " +
                date;//.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    protected abstract void snooze();
}
