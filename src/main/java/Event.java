import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    LocalDate date;

    Event(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    Event(String task, boolean done, LocalDate date) {
        super(task, done);
        this.date = date;
    }

    @Override
    String getSaveString() {
        return "[D] " + super.getSaveString() + " /at " + this.date;
    }
    @Override
    public String toString() {
        String dateString = "No date set";
        if (this.date != null) {
            DateTimeFormatter formatters = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            dateString = this.date.format(formatters);
        }

        return "[Event] " + super.toString() + " (at: " + dateString + ")";
    }
}
