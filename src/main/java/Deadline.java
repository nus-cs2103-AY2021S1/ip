import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the Deadline object, contains information about the due date. 
 */
public class Deadline extends Task {

    /**
     * Represents the due date.
     */
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts to a string format to be saved in a text file.
     * 
     * @return a string representation of the deadline object.
     */
    @Override
    public String saveAsString() {
        return "D" + super.saveAsString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}