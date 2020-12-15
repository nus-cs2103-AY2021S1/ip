package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    public Event(String description, LocalDateTime time) {
        super(description, time);
        super.type = "event";
    }

    public Event(String description, LocalDateTime time, boolean isDone) {
        this(description, time);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", (
                super.isDone ? "\u2713" : "\u2718"),
                super.description,
                super.time);
    }
}
