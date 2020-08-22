import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        LocalDate dateStart;
        LocalDate dateEnd;
        try {
            String[] startEnd = at.split(" to ");
            dateStart = LocalDate.parse(startEnd[0]);
            dateEnd = LocalDate.parse(startEnd[1]);
        } catch (DateTimeParseException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        this.at = dateStart.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to "
                + dateEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
