package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a type of task with a deadline
     *
     * @param description the content of the task
     * @param by          time in yyyy-mm-dd
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }


    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by.trim());
    }


    /**
     * Overrides the toString method
     *
     * @return a custom task description
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }


    /**
     * Returns a fixed format in string to store the task to storage.
     *
     * @return D | 0 | return book | June 6th
     */
    public String toCustomString() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
    }


}