package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDate date;

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Event(boolean isDone, String name, LocalDate date) {
        super(isDone, name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
