package main.java.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime deadline;

    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadlines(String task, String deadline, boolean isDone) {
        super(task,isDone);
        LocalDateTime dateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.deadline = dateTime;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String dateTime = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

}
