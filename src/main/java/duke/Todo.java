package duke;

import java.time.LocalDate;

/**
 * Represents an {@code Todo} object. Inherits from {@code Task} object.
 */
class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void setTime(LocalDate time) {
        // do nothing
    }

    @Override
    public Todo completeTask() {
        return new Todo(super.getDescription(), true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
