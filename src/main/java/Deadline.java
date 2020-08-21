<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that inherits from Task class, and has an additional condition, which is when it is due by.
 */
>>>>>>> branch-Level-8
public class Deadline extends Task {
    protected LocalDate by;

<<<<<<< HEAD
=======
    /**
     * Creates a new Deadline object
     *
     * @param description details about the Deadline
     * @param by date the deadline is due by in yyyy-mm-dd format
     * @return Deadline with a corresponding description and sets it as uncompleted.
     */
>>>>>>> branch-Level-8
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
<<<<<<< HEAD
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    @Override
    public String infoString() {
        String x = "0";
        if (isDone) {
            x = "1";
        }
        return "D | " + x +  " | " + this.description +  " | " + this.by;
=======
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
>>>>>>> branch-Level-8
    }
}
