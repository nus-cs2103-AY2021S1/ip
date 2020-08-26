package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /** Deadline of task in local date time */
    protected LocalDateTime by;

    /**
     * Constructs a new instance of Deadline task with description and deadline.
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description, Type.DEADLINE);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(
                "dd/MM/yyyy hh:mm a"));
    }

    /**
     * Constructs a new instance of Deadline task with description, deadline, status of completion.
     * @param description Description of task.
     * @param by Deadline of task.
     * @param isDone Status of completion.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, Type.DEADLINE, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(
                "dd/MM/yyyy hh:mm a"));
    }

    /**
     * Returns deadline of task.
     * @return Deadline of task.
     */
    @Override
    public String getTime() {
        return this.by.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
    }


    /**
     * Returns a string representation of a Deadline task.
     * @return String representation of a Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(
                        DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM)) + ")";
    }
}
