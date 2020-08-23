import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDateTime deadline;

    public Deadlines(String taskDesc, LocalDateTime deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+ deadline.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}
