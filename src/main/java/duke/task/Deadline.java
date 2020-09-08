package duke.task;

import java.time.LocalDateTime;
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

    protected Deadline(String task, LocalDateTime date) {
        super(task);
        this.date = date;
    }

    /**
     * Creates a Deadline object
     * @param task the task as per user input
     * @param date the deadline as per user input
     * @return a Deadline object
     */
        public static Deadline createDeadline(String task, LocalDateTime date) {
        assert date != null : "Assertion Failure: Deadline is Null";
        return new Deadline(task, date);
    }

    @Override
    public String toString() {
        String done = this.done ? "O" : "X";
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        if (isImportant) {
            return "[D][" + done + "]** " + this.task + "by: " + date;
        } else {
            return "[D][" + done + "] " + this.task + "by: " + date;
        }
    }
}
