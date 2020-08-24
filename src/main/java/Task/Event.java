package Task;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.atDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            this.atDate = null;
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        if (atDate == null) {
            return "[E]" + icon + " " + this.description + " (at: " + this.at + ")";
        } else {
            return "[E]" + icon + " " + this.description + " (at: "
                    + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
