import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    private String timing;
    private Event(String name, String timing) {
        super(name);
        this.timing = timing;
    }

    public static Event createEvent(String name, String timing) throws DukeException {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(timing);
        }
        catch (DateTimeParseException ex) {
            throw new DukeException("Please specify the date as follows: yyyy-mm-dd");
        }
        return new Event(name, localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String missingNameError() {
        return "The description of an event task cannot be empty.";
    }

    @Override
    public String toSaveDataFormat() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " E " + this.name + "\n" + this.timing;
        return saveData;
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        String eventTime = this.timing.length() > 0 ? " (at: " + this.timing + ")" : "";
        return "[E]" + marked + this.name + eventTime;
    }
}
