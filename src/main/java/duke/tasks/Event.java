package duke.tasks;

import duke.timeformatter.TimeFormatter;

import java.time.LocalDate;

/**
 * Represent a task that specifies a time of occurrence.
 */
public class Event extends Task {

    /**
     * Event's occurrence.
     */
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + super.toString() + "(at: "
                + TimeFormatter.prettyDate(at) + ")";
    }
}

