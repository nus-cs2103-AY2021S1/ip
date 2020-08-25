import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadline = null;

    public Deadline(String taskName, String deadlineString) {
        super(taskName);
        try {
            deadline = LocalDate.parse(deadlineString);
            this.deadlineString = deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            this.deadlineString = deadlineString;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }
}
