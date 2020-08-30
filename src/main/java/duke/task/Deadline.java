package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a particular type of Task, corresponding to tasks of the "Deadline" form
 */
public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadline = null;

    /**
     * Object representing Deadline tasks
     * @param taskName Name of Deadline task
     * @param deadlineString String representing deadline. Will be specially formatted if it's a valid date format
     */
    public Deadline(String taskName, String deadlineString) {
        super(taskName);
        try {
            deadline = LocalDate.parse(deadlineString);
            this.deadlineString = deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            this.deadlineString = deadlineString;
        }
    }

    /**
     * Gets a String which represents the Deadline in the appropriate format for storing to the hard disk
     * @return String in the storage format representing Deadline
     */
    @Override
    public String getStorageFormat() {
        return "D | " + super.getStorageFormat() + " | " + deadlineString;
    }

    /**
     * Provides string representation of Deadline, used for UI display
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }
}
