package duckie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task type in charge of task containing deadline date
 */
public class Deadline extends Task {
    protected final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    protected LocalDate deadline;

    /**
     * Instantiate a Deadline task
     * @param description Description of the task
     * @param deadline Deadline date of the task
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overrides method to return the type of the Deadline task
     * @return "D" string
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Overrides method to return the date of the Deadline task
     * @return Date string of Deadline Task
     */
    @Override
    public String getDate() {
        return dtf.format(this.deadline);
    }

    /**
     * Overrides method to return the String representation of a Deadline task
     * @return String representation of Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dtf.format(this.deadline) + ")";
    }
}
