package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline that is part of the Task class, regarding a deadline.
 */

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
