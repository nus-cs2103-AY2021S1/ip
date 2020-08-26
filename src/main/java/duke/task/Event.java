package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime date;


    public Event(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.getStatusIcon()
                + " "
                + super.toString()
                + "(at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }
}