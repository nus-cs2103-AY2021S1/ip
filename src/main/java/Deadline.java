import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate due;
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + due.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
