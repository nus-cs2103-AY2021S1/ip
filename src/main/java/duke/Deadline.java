package duke;

import java.time.LocalDate;

/**
 * Represents an {@code Deadline} object. Inherits from {@code Task} object
 */
class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs and initializes the attributes of a new Event object.
     * @param description The description of the task.
     * @param isDone The status of the task.
     * @param deadline The deadline time.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.by = deadline;
    }

    @Override
    public void setTime(LocalDate time) {
        this.by = time;
    }

    @Override
    public Deadline completeTask() {
        return new Deadline(super.getDescription(), true, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " on " + this.by.toString();
    }
}
