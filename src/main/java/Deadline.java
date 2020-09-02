import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates data for a deadline task.
 */

public class Deadline extends Task {

    protected LocalDate time;

    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public String getDate() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }
}
