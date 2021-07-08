package src.main.java.duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline task.
 *
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor for deadline with description and dueDate
     * @param description description of deadline
     * @param dueDate     duedate of the deadline task
     */
    public Deadline(String description, String dueDate) {
        this.description = description;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dueDate = LocalDateTime.parse(dueDate, dateFormat);
    }

    /**
     * Gets the string to be printed for the deadline.
     */
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + dueDate.format(dateFormat) + ")";
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Gets the string to be written for the deadline task.
     */
    public String toWriteString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D " + super.toWriteString() + " | " + dueDate.format(dateFormat);
    }
}
