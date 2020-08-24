package java1.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime at;

    public Events(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    public Events(String task, String at, boolean isDone) {
        super(task, isDone);
        LocalDateTime dateTime = LocalDateTime.parse(at, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.at = dateTime;
    }

    public LocalDateTime checkAt() {
        return this.at;
    }

    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
