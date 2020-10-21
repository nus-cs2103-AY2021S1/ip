package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of tasks that has a deadline.
 */
public class Deadline extends Task {

    protected LocalDate byDate;
    private String byString;

    /**
     * Construct a new deadline task from specified description and due date.
     * @param description the description of the task to be created
     * @param by the string indicates the deadline
     *           If the string <code>by</code> is of pattern that Duke understands,
     *           Duke will save the deadline as a date. Otherwise, Duke will
     *           understand this as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        try {
            this.byString = by;
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    public boolean hasDate() {
        return byDate != null;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public String getByString() {
        return byString;
    }

    /**
     * Return the string that is intended to be stored in the local database.
     * @return the string to be stored in the local database, the format is
     *         understandable for <code>Storage</code>
     */
    @Override
    public String toDataString() {
        if (super.isDone) {
            return "D | 1 | " + description + " | " + byString;
        } else {
            return "D | 0 | " + description + " | " + byString;
        }
    }

    /**
     * Return the string representation of the task.
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        if (this.byDate != null) {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + byString + ")";
        }
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
