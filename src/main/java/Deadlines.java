import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDate deadline;
    protected String due;

    public Deadlines(String description, String deadline) {
        super(description);
        this.due = deadline;
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.due = deadline;
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String writeToFile() { return "D" + super.writeToFile() + " | " + due; }
}
