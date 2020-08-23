//  Deadlines: tasks that need to be done before a specific date/time
//  e.g., submit report by 11/10/2019 5pm

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;
    protected String deadlineStr;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.deadlineStr = deadline.format(super.formatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%1$s (by: %2$s)", super.toString(), this.deadlineStr);
    }
}