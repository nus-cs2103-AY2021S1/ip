import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected String at;
    protected LocalDate date;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
