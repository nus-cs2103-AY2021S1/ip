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

    public static Event parse(String[] split) {
        try {
            Event event = new Event(split[2], split[3]);
            if (split[1].equals("1")) {
                event.markDone();
            }
            return event;
        } catch (DukeException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String serialize() {
        return "E|" + super.serialize() + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
