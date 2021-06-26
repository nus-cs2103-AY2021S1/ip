package duke.task;

import java.util.Date;

/**
 * This class represents a Deadline, which is a Task that needs to be done by a specified date and time.
 */
public class Deadline extends Task {
    private Date date;

    /**
     * Creates a Deadline.
     *
     * @param description Description of the Deadline.
     * @param isDone Whether the Deadline is due.
     * @param date Date of the Deadline.
     */
    public Deadline(String description, boolean isDone, Date date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Creates a Deadline which has not been completed.
     *
     * @param description Description of the Deadline.
     * @param date Date of the Deadline.
     */
    public Deadline(String description, Date date) {
        this(description, false, date);
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (by: %s)", DateHelper.formatDate(date));
    }

    @Override
    protected String taskTypeString() {
        return "D";
    }

    /**
     * Returns the Date when this Deadline is due.
     *
     * @return Due Date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Changes the Date for this Deadline.
     *
     * @param date New due Date.
     */
    public void setDate(Date date) {
        this.date = date;
        onChange();
    }
}
