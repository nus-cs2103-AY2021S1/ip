import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// a task that need to specify a date to be done before the date
public class Deadline extends Task {

    protected LocalDate deadlineDate;

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(boolean isDone, String description, LocalDate deadlineDate) {
        super(isDone, description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}