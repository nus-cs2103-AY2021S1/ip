import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, LocalDateTime dueDate, boolean isCompleted) {
        super(description, isCompleted);
        this.dueDate = dueDate;
    }

    @Override
    public Deadline markCompleted() {
        return new Deadline(description, dueDate, true);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
