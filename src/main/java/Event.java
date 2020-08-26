import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String atString;
    protected LocalDate atDate;

    Event(String task, String date) {
        super(task);
        this.atString = date;
        this.atDate = null;
    }

    Event(String task, LocalDate date) {
        super(task);
        this.atString = null;
        this.atDate = date;
    }

    public String getDate() {
        if (atString != null) {
            return atString;
        } else {
            return atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        if (atDate != null) {
            return "[E]" + super.toString() + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + atString.toString() + ")";
        }
    }
}
