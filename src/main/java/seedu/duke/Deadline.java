package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class extends Task class.
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    String dueDate;
    LocalDate localDate;

    /**
     * Constructor of the class.
     * Creates a Deadline object with task name, completion status and due date.
     * @param name Name of the Deadline task.
     * @param status Completion status (whether it is completed).
     * @param dueDate Due date of the task.
     */
    public Deadline(String name, Status status, String dueDate) {
        super(name, status);
        this.dueDate = dueDate;
        this.localDate = LocalDate.parse(dueDate);
    }

    /**
     * String value to represent the object when printed for user.
     * @return String value that represents the Deadline object.
     */
    @Override public String toString() {
        return "[D] " + this.status.statusToSymbol() + this.name +
                " by: " +
                localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }

    /**
     * Generates a String that represents the object to be stored in the data management file.
     * @return A String to be written into the text file.
     */
    @Override public String toStore() {
        return "[D] " + this.status.statusToSymbol() + this.name +
                " by: " + dueDate;
    }

}
