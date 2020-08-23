import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printDate() + ")";
    }

}
