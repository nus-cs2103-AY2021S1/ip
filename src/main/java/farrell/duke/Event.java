package main.java.farrell.duke;

import java.time.LocalDate;

/**
 * Encapsulates data for an event task
 */
public class Event extends TimedTask {
    Event(String description, LocalDate time) {
        this(description, false, time);
    }

    Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
        taskType = TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
