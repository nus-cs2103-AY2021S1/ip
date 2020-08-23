import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    LocalDateTime datetime;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String content, String datetimeString) throws DukeException{
        super(content);
        if (datetimeString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetime = LocalDateTime.parse(datetimeString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }

    @Override
    public String toString() {

        return String.format("[E]%s (at: %s)", super.toString(), datetime.format(formatter));
    }
}
