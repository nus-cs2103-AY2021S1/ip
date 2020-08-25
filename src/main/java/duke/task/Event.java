package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String name, LocalDate duration) {
        super(name);
        this.duration = duration;
    }

    public LocalDate getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}