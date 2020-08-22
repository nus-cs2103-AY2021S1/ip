/**
 * Represents a deadline with inherited functionalities from Task.
 * @author Lim Zi Yang
 */
public class Deadline extends Task {
    private final String dateAndTime;

    /**
     * Creates an undone deadline.
     * @param description Description of the deadline.
     * @param dateAndTime Date and time of the deadline.
     */
    Deadline (String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Creates a deadline.
     * @param description Description of the deadline.
     * @param dateAndTime Date and time of deadline.
     * @param isDone Whether the deadline is done.
     */
    private Deadline (String description,  String dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Marks deadline as done.
     * @return A done deadline.
     */
    @Override
    public Deadline markDone() {
        return new Deadline(getDescription(),  this.dateAndTime, true);
    }

    /**
     * Convert to string value of deadline to be stored as data.
     * @return String to be stored in hard disk.
     */
    @Override
    public String convertToStringData() {
        return checkIsDone()
                ? "D/1/" + getDescription() + "/" + this.dateAndTime
                : "D/0/" + getDescription() + "/" + this.dateAndTime;
    }

    /**
     * Overridden toString method.
     * @return String value of deadline.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " +  dateAndTime + ")";
    }
}
