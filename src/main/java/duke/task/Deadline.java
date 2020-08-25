package duke.task;

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
     * @param by          date the deadline is due by in yyyy-mm-dd format
     * @return Deadline with a corresponding description and sets it as uncompleted.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }

    /**
     * Creates a new Event object
     *
     * @param description details about the Deadline
     * @param isDone whether Deadline is done or not
     * @param by time/date the Deadline is due by in yyyy-mm-dd format
     * @return Deadline with a corresponding description and completed status.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by.trim());
    }

    public boolean haveKeyword(String keyword) {
        return description.contains(keyword) || by.format(DateTimeFormatter.ofPattern("d MMM yyyy")).contains(keyword);
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
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
        return "D | " + x +  " | " + this.description + " | " + by;
    }
}
