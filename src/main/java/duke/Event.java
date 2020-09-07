package duke;

import java.time.LocalDate;

/**
 * Represents an {@code Event} object. Inherits from {@code Task} object.
 */
class Event extends Task {
    private LocalDate at;

    /**
     * Constructs and initializes the attributes of a new Event object.
     * @param description The description of the task.
     * @param isDone The status of the task.
     * @param time The event time.
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.at = time;
    }

    @Override
    public void setTime(LocalDate time) {
        this.at = time;
    }

    @Override
    public Event completeTask() {
        return new Event(super.getDescription(), true, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " on " + this.at.toString();
    }
}
