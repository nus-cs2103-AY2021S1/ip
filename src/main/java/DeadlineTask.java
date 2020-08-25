package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected String stringDeadline;
    protected LocalDateTime deadline;

    public DeadlineTask(String deadline, String task, TaskSymbol taskType) {
        super(task, taskType);
        this.stringDeadline = deadline;
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) +  ")";
    }
}
