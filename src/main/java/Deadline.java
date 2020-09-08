import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String in, LocalDateTime deadline) {
        super(in);
        this.deadline = deadline;
    }

    public String toString() {
        String doneStatus;
        if (isDone) {
            doneStatus = "✓";
        } else {
            doneStatus = "✗";
        }
        return "[D] [" + doneStatus + "] " + task + " (by: " + dateToString(deadline) + ")";
    }

    public String saveText() {
        return "D | " + super.saveText() + " | " + dateToSave(deadline);
    }
}
