package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task associated with an event.
 * The instance of the Event class has the description and
 * the time of the event.
 */
public class Event extends Task{
    protected LocalDate at;

    /**
     * Takes in the description and time of an event and returns an instance of
     * the corresponding type of task.
     *
     * @param description The description of the event.
     * @param at The time of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a representation of the task in terms of the description and state.
     *
     * @return The representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a representation of the task in terms of the description and
     * state for the task to be stored into the record.
     *
     * @return The representation of the task in terms of
     *         the description, the state of completion, and time.
     */
    @Override
    public String record() {
        if(this.isCompleted) {
            return "E | 1 | " + description + " | " + at;
        } else {
            return "E | 0 | " + description + " | " + at;
        }
    }

    /**
     * Returns the boolean value that describes whether the task is on
     * the specific date.
     *
     * @param localDate The specific date.
     * @return The boolean value that describes whether the task is on
     * the specific date.
     */
    @Override
    public boolean isAt(LocalDate localDate) {
        return this.at.equals(localDate);
    }
}
