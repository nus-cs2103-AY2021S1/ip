package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String details, LocalDateTime date) {
        super(details);
        this.date = date;
    }

    public Event(String details, boolean isDone, LocalDateTime date) {
        super(details, isDone);
        this.date = date;
    }

    @Override
    public String store() {
        String done = this.isDone ? "T " : "F ";
        return "E " + done + this.details + " /at " + this.date + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)) + ")";
    }
}
