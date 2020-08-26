package duke.task;

import java.time.LocalDate;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Always returns false as a todo has no date.
     * @param date A date
     * @return false
     */
    @Override
    public boolean isDue(LocalDate date) {
        return false;
    }

    @Override
    public String toSaveData() {
        return "T | " + super.toSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
