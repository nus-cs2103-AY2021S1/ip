package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone,String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        return String.format("E / %d / %s / %s",isDone ? 1 : 0, this.desciption,this.at);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
