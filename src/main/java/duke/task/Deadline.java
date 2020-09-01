package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline that is part of the Task class, regarding a deadline.
 */

public class Deadline extends Task {
    protected LocalDate date;

    /**
     * The constructor to make a Deadline task.
     * @param description the specific details of the task
     * @param by the due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);

    }

    /**
     * @return a string indicating the details of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
