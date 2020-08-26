import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate taskAt;

    Event(String taskName, LocalDate taskAt) {
        super(taskName);
        this.taskAt = taskAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.taskAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}