package duke;

import java.time.LocalDate;

class Event extends Task {

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
