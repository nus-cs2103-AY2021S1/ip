import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Represents an Deadline</h1>
 *  * Contains the details of the deadline, whether it is completed,
 *  * and the due date.
 */
public class Deadline extends Task {
    protected String date;
    private LocalDate localDate;

    public Deadline(String task, String date, boolean isCompleted) {
        super(task, isCompleted);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    /**
     * An overridden method that returns a String with the Task type,
     * deadline details, due date formatted in MMM d yyyy format.
     * @return String with all details of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
