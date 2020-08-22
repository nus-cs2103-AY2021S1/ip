import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class.
 * Represents a deadline task.
 * Extends from Task.
 *
 * @author YanCheng
 */
public class Deadline extends Task {

    LocalDate date;

    /**
     * Constructor for Deadline.
     * @param taskName name of deadline
     * @param date date of deadline
     */
    public Deadline(String taskName, String date) {
        // date format must be in YYYY-MM-DD e.g. 2020-08-22
        super(taskName.stripTrailing());
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Date is in the wrong format");
        }
    }

    /**
     * Return deadline information to be stored locally.
     * @return deadline information
     */
    @Override
    public String getInfo() {
        return String.format("D | %d | %s | %s\n", isDone ? 1 : 0, taskName, date.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
