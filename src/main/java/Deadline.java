package main.java;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern(" MMM dd yyyy"));
    }

    @Override
    public void printDescription() {
        System.out.println("[D][" + getStatusIcon()
                + "]" + description + "(by:" + getDeadline() + ")");
    }
}
