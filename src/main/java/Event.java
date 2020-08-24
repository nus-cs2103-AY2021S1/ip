import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate date) {
        super(description);
        this.at = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
            + " (at: " + at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}
