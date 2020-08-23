package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Create Event objects extends from Task class.
 */
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Create a new Event object with isDone equal true.
     *
     * @return new Event object.
     */
    @Override
    protected Event markAsDone() {
        return new Event(super.description, this.at, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "[" + at.getDayOfWeek() + "])";
    }
}

