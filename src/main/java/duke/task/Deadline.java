package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline, a task that need to be done before a specific date and time.
 */
public class Deadline extends Task {
    /**
     * The Deadline.
     */
    private final LocalDate deadline;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description.
     * @param deadline    the deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description.
     * @param deadline    the deadline.
     * @param isDone      true if deadline is done, false otherwise.
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns a boolean which is true if date is equal, false otherwise.
     *
     * @param date the date.
     * @return the boolean.
     */
    public boolean isDateEqual(LocalDate date) {
        return date.equals(this.deadline);
    }

    /**
     * Returns the formatted deadline.
     *
     * @return the formatted deadline.
     */
    private String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return deadline.format(formatter);
    }

    /**
     * Returns data of the event.
     * Used to store the event.
     *
     * @return the data.
     */
    @Override
    public String getData() {
        return String.format("%s_%s ", super.getData(), deadline);
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getFormattedDeadline() + ")";
    }
}
