package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events are tasks that start at a specific time and ends at a specific time
 *
 * @author Lio
 */
class Event extends Task {
    LocalDate at;

    /**
     * Constructor
     *
     * @param name Name of the task
     * @param at Date that the task occurs
     */
    public Event(String name, String at) {
        super(name);
        this.at = LocalDate.parse(at);
    }

    /**
     * Converts the task to data form
     */
    public String toData() {
        return "E | " + super.toData() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
