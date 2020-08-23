import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent a deadline object
 * @author vanGoghhh
 */

public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor for deadline class
     * @param description description of the deadline
     * @param by date that the deadline is dued
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the duedate of the deadline
     * @return duedate of the deadline
     */
    protected LocalDate getBy() {
        return this.by;
    }

    /**
     * Prints the deadline object
     * @return string representation of a deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }

}