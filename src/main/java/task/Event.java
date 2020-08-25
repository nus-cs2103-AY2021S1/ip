package main.java.task;

import java.time.LocalDateTime;

class Event extends Task {
    public Event(String description, LocalDateTime time) {
        super(description, time);
        super.type = "event";
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                (super.isDone ? "\u2713" : "\u2718"),
                super.description,
                super.time);
    }
}
