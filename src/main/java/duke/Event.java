package duke;

import java.time.LocalDate;

public class Event extends TimedTask {

    public Event(String description, LocalDate date) {
        super(description, date);
    }

    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone, date);
    }

    @Override
    public String toText() {
        super.toText("E");
    }

    @Override
    public String toString() {
        super.toString("E");
    }
}
