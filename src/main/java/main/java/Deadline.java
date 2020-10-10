package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A kind of the tasks, which has a specific deadline
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
        this.isDone = isDone;
    }

    /**
     * gives the String representing the deadline
     * @return a String representing the deadline
     */
    private String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern(" MMM dd yyyy"));
    }

    @Override
    public String getDescription() {
        return "[D][" + getStatusIcon()
                + "]" + description + "(by:" + getDeadline() + ")";
    }

    @Override
    public void printDescription() {
        System.out.println(getDescription());
    }
}
