package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    public LocalDateTime timeAt;

    // TODO: 17/8/20 make a toString 
    public Event(String desc, String timeAt) throws ParseException {
        super(desc);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.timeAt = LocalDateTime.parse(timeAt, format);
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[E][" + sign + "] " + description + " (at:" + timeAt + ")";
    }
}