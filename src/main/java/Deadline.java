import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDateTime timeBy;

    public Deadline(String desc, String timeBy) throws DateTimeParseException {
        super(desc);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.timeBy = LocalDateTime.parse(timeBy, format);
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[D][" + sign + "] " + description + " (by:" + timeBy + ")";
    }
}
