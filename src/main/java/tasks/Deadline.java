package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    /**
     * Constructor to instantiate a new deadline object
     *
     * @param work A String containing the description of the deadline
     * @param date A datetime object parsed in from user input
     */
    public Deadline(String work, LocalDateTime date) {
        super(work, date);
    }

    /**
     * Get the description of the deadline
     *
     * @return String containg the description
     */
    public String description() {
        return super.isDone()
                ? "[D][0]"
                : "[D][1]";
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + super.getDate() + ")";
    }
}
