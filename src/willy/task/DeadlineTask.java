package willy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task with a deadline.
 */
public class DeadlineTask extends Task {
    public String stringDeadline;
    public LocalDateTime deadline;

    public DeadlineTask(String deadline, String task, TaskSymbol taskType) {
        super(task, taskType);
        this.stringDeadline = deadline;
        this.deadline = LocalDateTime.parse(deadline,
                DateTimeFormatter.
                        ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.
                        ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }
}
