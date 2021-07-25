import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final String description;
    private final LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.description = description;
        this.date = date;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {

        return "[D]" + "[" + getStatusIcon() + "]" + "[" + this.getPriority() + "]" + " " + this.description + "(by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

