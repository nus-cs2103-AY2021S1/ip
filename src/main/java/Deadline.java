import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadlineDate;

    public Deadline(String description, String by) {
        super(description);
        this.deadlineDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
