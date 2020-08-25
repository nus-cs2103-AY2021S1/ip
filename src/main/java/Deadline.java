import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime taskBy;

    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.taskBy = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.taskBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
