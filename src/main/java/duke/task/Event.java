package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy @ h:mma")) + ")";
    }

    @Override
    public String convertToData() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + time.toString();
    }
}
