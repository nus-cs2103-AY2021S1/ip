package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDateTime date;

    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.getStatusIcon()
                + " " + super.toString()
                + "(by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }
}