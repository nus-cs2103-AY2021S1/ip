package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * An extension of the Task class with an additional field specifying the deadline of the task.
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    protected String by;
    /** The deadline of the task parsed as a LocalDate. */
    protected LocalDateTime deadline;

    /**
     * Constructs a new Deadline object.
     * @param description {@inheritDoc}
     * @param by The deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            deadline = LocalDateTime.parse(by).truncatedTo(ChronoUnit.MINUTES);
        } catch (DateTimeParseException e) {
            try {
                deadline = LocalDate.parse(by).atTime(23, 59);
            } catch (DateTimeParseException e1) {
                // Do nothing, since there is no deterministic way to convert all possible
                // strings into a LocalDateTime object meaningfully without adding more
                // prohibitions to the user input.
            }
        }
    }

    /**
     * Converts the deadline to the format "%B %d %Y HH:MM", such as JANUARY 1 2000 12:34, if available.
     *
     * @return The deadline in the desired format if parsable; the original string otherwise.
     */
    public String getBy() {
        if (deadline == null) {
            return by;
        }
        return String.format("%s %02d %d %02d:%02d",
                deadline.getMonth().toString(),
                deadline.getDayOfMonth(),
                deadline.getYear(),
                deadline.getHour(),
                deadline.getMinute());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        int stat = 0;
        if (isDone) {
            stat = 1;
        }
        return String.format("%s | %d | %s by %s", getType(), stat, description, getBy());
    }
}
