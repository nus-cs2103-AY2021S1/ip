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
            this.deadlineDate = LocalDate.parse(by);
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
            System.out.println(this.deadlineDate.getMonth().toString()
            + " " + this.deadlineDate.getDayOfMonth()
            + " " + this.deadlineDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        int stat;
        if (this.isDone) {
            stat = 1;
        } else {
            stat = 0;
        }
        return String.format("%s | %d | %s by %s", this.getType(), stat,
                this.description, this.by);
    }
}
