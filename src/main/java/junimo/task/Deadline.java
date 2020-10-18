package junimo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline object which inherits from Task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs an instance of Deadline object.
     * @param description Description of task.
     * @param by Due date for task.
     * @param isDone Whether task is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS! The deadline in an incorrect format! "
                    + "Please indicate the date as <yyyy-MM-dd>");
        }
    }

    /**
     * Implements method specified by abstract class Task.
     * @return String of Deadline object in format for saving to and retrieving from hard disk.
     */
    public String getParsedTask() {
        return "deadline " + description + " /by " + by + System.lineSeparator()
                + isDone + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Overrides Object and Task equals method.
     * @param other Object compared to.
     * @return True if other is also a Deadline object with the same description, by and isDone fields. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return description.equals(otherDeadline.description)
                    && isDone == otherDeadline.isDone
                    && by.equals(otherDeadline.by);
        } else {
            return false;
        }
    }
}
