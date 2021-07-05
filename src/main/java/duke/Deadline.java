package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class contains the name, completion status and deadline of the task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor of a Deadline object.
     * 
     * @param description The description of the task.
     * @param by The deadine of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of a Deadline object to be stored in the storage.
     * 
     * @return A String representing the code of the task stored in the storage.
     */
    @Override
    public String encode() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("D | %s | %s | %s", isDone ? 1 : 0, description, dateFormatter.format(by));
    }

    /**
     * Returns a string representation of a Deadline object.
     * 
     * @return A String containing the description and completion status of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}