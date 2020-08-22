import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDate at;

    public Event(String name, String at) throws DukeException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
