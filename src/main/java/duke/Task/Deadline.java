package duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Deadline task that takes in a description and date.
 */
public class Deadline extends Task {

    LocalDate date;

    public Deadline(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    public Deadline(String task, boolean done, LocalDate date) {
        super(task, done);
        this.date = date;
    }

    @Override
    public String getSaveString() {
        return "[D] " + super.getSaveString() + " /by " + this.date;
    }

    @Override
    public String toString() {
        String dateString = "No date set";
        if (this.date != null) {
            DateTimeFormatter formatters = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            dateString = this.date.format(formatters);
        }

        return "[Deadline] " + super.toString() + " (by: " + dateString + ")";
    }
}
