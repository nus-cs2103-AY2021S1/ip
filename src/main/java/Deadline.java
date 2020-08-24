import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    public LocalDate due;
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    public Deadline(boolean done, String description, LocalDate due) {
        super(done, description,'D');
        this.due = due;
        String unparseMessage = "D";
        if (done) {
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
