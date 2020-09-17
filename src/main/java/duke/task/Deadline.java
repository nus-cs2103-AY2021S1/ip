package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a deadline that has additional LocalDate field and extends Task class.
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;
    private String by;

    /**
     * Constructor for Deadline class.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadlineDate = LocalDate.parse(by);
    }

    /**
     * Constructor for Deadline class that can initialize the value of isDone.
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.deadlineDate = LocalDate.parse(by);
    }

    /**
     * Return the data of this deadline task to be stored in the storage.
     * @return data of this deadline task as a String
     */
    @Override
    public String getData() {
        return "d/" + super.getData() + "/" + this.by;
    }

    /**
     * Return the string representation of this deadline task.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + ")";
    }
}
