package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A sub class of Task, which represents any deadlines.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Deadline constructor.
     *
     * @param description Details of the task.
     * @param isDone      Progress of the task.
     * @param date        Date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.getStatusIcon()
                + " " + super.toString()
                + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm"))
                + ")";
    }
}
