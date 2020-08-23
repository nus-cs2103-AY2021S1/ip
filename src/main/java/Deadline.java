import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDateTime datetimeDue;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String content, String datetimeDueString, boolean isComplete) throws DukeException {
        super(content, isComplete);
        if (datetimeDueString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetimeDue = LocalDateTime.parse(datetimeDueString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }


    public Deadline(String content, String datetimeDueString) throws DukeException {
        super(content);
        if (datetimeDueString.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        try {
            this.datetimeDue = LocalDateTime.parse(datetimeDueString, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime specified (Needs to be yyyy-MM-dd HH:mm).");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetimeDue.format(formatter));
    }

    @Override
    public String toSaveString() {
        return String.format("D/%s/%s", super.toSaveString(), datetimeDue);
    }
}
