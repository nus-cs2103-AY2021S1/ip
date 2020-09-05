package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * An extension of the Task class with an additional field specifying the deadline of the task.
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    protected String by;
    /** The deadline of the task parsed as a LocalDate. */
    protected LocalDate deadlineDate;

    /**
     * Constructs a new Deadline object.
     * @param description {@inheritDoc}
     * @param by The deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            deadlineDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            // Do nothing, since there is no deterministic way to convert all possible
            // strings into a LocalDate object meaningfully without adding more
            // prohibitions to the user input.
        }
    }

    /**
     * Prints the deadline in the format "%B %d %Y", such as JANUARY 1 2000, if available.
     * Otherwise, shows an error message.
     */
    public void printTime() {
        try {
            System.out.println(deadlineDate.getMonth().toString()
                + " " + deadlineDate.getDayOfMonth()
                + " " + deadlineDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
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
        return String.format("%s | %d | %s by %s", getType(), stat, description, by);
    }
}
