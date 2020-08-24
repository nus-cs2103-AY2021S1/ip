import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate due;
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    public Deadline(boolean isDone, String description, LocalDate due) {
        super(isDone, description,'D');
        this.due = due;
        String unparseMessage = "D";
        if (isDone) {
            unparseMessage += "1";
        } else {
            unparseMessage += "0";
        }
        unparseMessage += description;
        unparseMessage += ",";
        unparseMessage += due;
        super.unparseMessage = unparseMessage;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + due.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
