import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;

    public Event(String description, String at) throws DukeException {
        super(description);
        String[] startEnd = at.split(" to ");
        LocalDateTime timeStart;
        LocalDateTime timeEnd;
        try {
            timeStart = LocalDateTime.parse(startEnd[0], DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
            timeEnd = LocalDateTime.parse(startEnd[1], DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        } catch (DateTimeParseException ex) {
            throw new DukeException("Event timing details cannot be parsed");
        }
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        String at = timeStart.format(DateTimeFormatter.ofPattern("MMM d yyyy kk:mm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy kk:mm"));
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        String at = timeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        return "E" + separator + isDone + separator + super.description + separator + at + "\n";
    }
}
