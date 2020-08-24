import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    LocalDate date;

    Deadline(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    Deadline(String task, boolean done, LocalDate date) {
        super(task, done);
        this.date = date;
    }

    @Override
    String getSaveString() {
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
