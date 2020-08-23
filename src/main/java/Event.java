import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        LocalDate dateStart = null;
        LocalDate dateEnd = null;
        String[] startEnd = at.split(" to ");
        try {
            dateStart = LocalDate.parse(startEnd[0]);
            dateEnd = LocalDate.parse(startEnd[1]);
        } catch (DateTimeParseException ex) {
            try {
                dateStart = LocalDate.parse(startEnd[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                dateEnd = LocalDate.parse(startEnd[1], DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException exc) {
                throw new DukeException("Event timing details cannot be parsed");
            }
        }
        this.at = dateStart.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to "
                + dateEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        return "E" + separator + isDone + separator + super.description + separator + at + "\n";
    }
}
