package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Deadline Task class </h1>
 *
 * The deadline class is a child class of Task
 * It contains an extra property: deadline that indicates
 * the date that a task is due.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Deadline extends Task {

    protected LocalDate deadline;

    protected Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Creates a Deadline object
     * @param task the task as per user input
     * @param deadline the deadline as per user input
     * @return a Deadline object
     */
    public static Deadline createDeadline(String task, LocalDate deadline) {
        assert deadline != null : "Assertion Failure: Deadline is Null";
        return new Deadline(task, deadline);
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        String date = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + done + "] " + this.task + "(by: " + date + ")";
    }
}
