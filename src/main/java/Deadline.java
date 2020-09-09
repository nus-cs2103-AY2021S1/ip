package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    private String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern(" MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon()
                + "]" + description + "(by:" + getDeadline() + ")";
    }

    @Override
    public void printDescription() {
        System.out.println(toString());
    }
}
