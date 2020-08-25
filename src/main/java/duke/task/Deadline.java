package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A kind of tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Default constructor for Deadline.
     * @param description the description of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try{
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            byDate = null;
        }
    }

    /**
     * Construct a Deadline task assigned the done status.
     * @param isDone the done status of the task.
     * @param description the description of the task.
     */
    public Deadline(boolean isDone, String description, String by) {
        this(description, by);
        this.isDone = isDone;
    }

    /**
     * Return the String to be store in files.
     * @return the String to be store in files.
     */
    @Override
    public String toStore() {
        String div = " | ";
        return "D" + div + (isDone ? "1" : "0") + div + description + div + by;
    }

    /**
     * Return the String represents the task.
     * @return the String represents the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (byDate == null ? by : byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }
}
