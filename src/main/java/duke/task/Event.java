package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    LocalDateTime time;

    public Event(String description, String time, boolean isDone)
            throws DateTimeParseException {
        super(description, "E", isDone);
        this.time = LocalDateTime.parse(time,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toSave() {
        return String.format("%s | %s", super.toSave(),
                this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }

}