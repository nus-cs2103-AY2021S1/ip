package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * The due date associated with the Task
     */
    private LocalDateTime date;

    /**
     * Creates a new instance of a Deadline object with attributes defined
     * in the parameters.
     * @param description Description of the deadline task.
     * @param date Date that the task is due by.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Creates a new instance of a Deadline object with attributes defined
     * in the parameters.
     * Overloaded constructor which specifies the completion status of the task.
     * @param description Description of the task.
     * @param date Date that the task is due by.
     * @param done Completion status of the task.
     */
    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Retrieves the due date of the task.
     * @return Returns the date.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Overrides the default toString() method in the Task class.
     * @return Returns a String describing the attributes of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
