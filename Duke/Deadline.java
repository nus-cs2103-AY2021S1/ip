package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class that represents a Deadline task.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;
    public Deadline(String description, String dueDate) {
        this.description = description;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dueDate = LocalDateTime.parse(dueDate, dateFormat);
    }

    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + dueDate.format(dateFormat) + ")";
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * This method returns the string to be written for the deadline task.
     */
    public String toWriteString() { return "D " +  super.toWriteString() + " | " + dueDate; }
}
