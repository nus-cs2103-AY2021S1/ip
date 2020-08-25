import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneSymbol, getName(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
