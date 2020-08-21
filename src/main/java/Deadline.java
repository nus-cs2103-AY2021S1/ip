import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that inherits from Task class, and has an additional condition, which is when it is due by.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline object
     *
     * @param description details about the Deadline
     * @param by date the deadline is due by in yyyy-mm-dd format
     * @return Deadline with a corresponding description and sets it as uncompleted.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
