import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, LocalDate atDate) {
        super(description);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        String output = "[E]" + super.toString() + " (at: ";
        if (this.atDate == null) {
            output += this.at;
        } else {
            output += this.atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return output + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E | " + super.getSaveFormat() + " | " + this.at;
    }
}
