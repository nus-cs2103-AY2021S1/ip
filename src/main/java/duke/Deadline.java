package duke;

import java.time.LocalDate;

/**
 * Represents an {@code Deadline} object. Inherits from {@code Task} object
 */
class Deadline extends Task {

    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}