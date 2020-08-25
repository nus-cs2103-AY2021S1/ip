package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The Deadline class acts as a task that has a deadline.
 * It extends the parent class Task and has a LocalDate by to represent the specified deadline
 */

public class Deadline extends Task {

    public LocalDate by;

    /**
     * Constructor for new deadline.
     * @param description   the description of the deadline task
     * @param by            the specified deadline for the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for loading deadlines.
     * @param description   the description of the deadline task
     * @param by            the specified deadline for the task
     * @param done          specifies whether the task is completed
     */
    public Deadline(String done, String description, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = (done == "1");
    }

    /**
     * Display deadline.
     * @return deadline in a string format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * How deadline is written into the text file.
     * @return deadline in a string format
     */
    @Override
    public String splitToString() {
        return "\n" + "D" + "/" + this.ifDone() + "/" + this.description + "/" + this.by.toString();
    }
}