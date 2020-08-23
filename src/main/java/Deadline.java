import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a subtype of Task which has a stipulated deadline
 * for the task to be completed by.
 */

public class Deadline extends Task {

    /** The deadline of the task. */
    protected LocalDate byDate;

    /** The deadline time of the task. */
    protected LocalTime byTime;

    /**
     * Constructor for Deadline.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) throws DukeException {
        super("D", description);
        try {
            String[] dateTime = by.split(" ");
            String dateValue = dateTime[0];
            if (dateValue.equals("today")) {
                dateValue = LocalDate.now().toString();
            } else if (dateValue.equals("tomorrow")) {
                dateValue = LocalDate.now().plusDays(1).toString();
            }
            this.byDate = LocalDate.parse(dateValue);
            this.byTime = LocalTime.parse(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        }
    }

    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super("D", description, isDone);
        try {
            String[] dateTime = by.split(" ");
            String dateValue = dateTime[0];
            if (dateValue.equals("today")) {
                dateValue = LocalDate.now().toString();
            } else if (dateValue.equals("tomorrow")) {
                dateValue = LocalDate.now().plusDays(1).toString();
            }
            this.byDate = LocalDate.parse(dateValue);
            this.byTime = LocalTime.parse(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        }
    }

    @Override
    public String getDescription() {
        return description + " / " + byDate + " " + byTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s, %s)",
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                byTime.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
}
