package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone, TaskType.EVENT);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String getTime() {
        return at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String printTime() {
        return at.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), printTime());
    }
}
