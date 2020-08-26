import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String name, String deadline) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
