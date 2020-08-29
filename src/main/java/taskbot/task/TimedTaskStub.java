package taskbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A stub to simulate tasks with time limits.
 */
public class TimedTaskStub extends TimedTask {
    public TimedTaskStub(String task, LocalDateTime time) {
        super(task, time);
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Timed Task(test): " + super.getTask() +
                "(Time: " + getTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }
}
