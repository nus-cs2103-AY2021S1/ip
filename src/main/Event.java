import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter dates in yyyy-mm-dd format!\n");
        }
    }

    @Override
    public String toData() {
        return "E | " + super.toData() + " | " + at;
    }

    @Override
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
