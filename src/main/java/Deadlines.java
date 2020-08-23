import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDate deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
