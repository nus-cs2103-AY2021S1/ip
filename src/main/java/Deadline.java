import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles deadline-based Tasks.
 */

public class Deadline extends DatedTask {
    /**
     * Constructor for Deadline-based Tasks.
     * @param name Description of Task.
     * @param deadline Deadline of Task.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name, deadline);
    }

    /**
     * Constructor for Deadline-based Tasks.
     * @param name Description of Task.
     * @param completed State of completion of Task.
     * @param deadline Deadline of Task.
     */
    public Deadline(String name, boolean completed, LocalDate deadline) {
        super(name, completed, deadline);
    }

    /**
     * Represents Deadline in format to be saved.
     * @return Saved representation of Deadline object.
     */
    @Override
    public String format() {
        return "D" + SAVE_DELIMITER + super.format();
    }

    /**
     * Represents Deadline in String form.
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
