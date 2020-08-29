package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");

    /**
     * Constructor to instantiate a new deadline object
     * @param work
     * @param date
     */
    public Deadline(String work, LocalDateTime date) {
        super(work, date);
        this.deadline = date;
    }

    /**
     * Get the description of the deadline
     * @return String containg the description
     */
    public String description() {
        return super.isDone()
                ? "[D][0]"
                : "[D][1]";
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(format) + ")";
    }
}
