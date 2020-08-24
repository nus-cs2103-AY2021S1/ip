import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    private Deadline (String task, LocalDateTime deadline, boolean isDone) {
        super(task, isDone);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(task, deadline, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
    }
}