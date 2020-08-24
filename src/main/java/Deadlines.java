import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadlines that the user wants to remember
 */
public class Deadlines extends Task {

    protected LocalDateTime deadline;

    public Deadlines(String taskDesc, LocalDateTime deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "+ deadline.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}
