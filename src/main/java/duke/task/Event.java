package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    String originalDate;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: "
                + at.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"))
                + ")";
    }

    @Override
    public String write() {
        return "\nevent," + super.write() + "," + originalDate;
    }
}
