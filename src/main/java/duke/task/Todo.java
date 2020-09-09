package duke.task;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    public Todo(String description) {
        this(description, TaskPriority.NONE, Collections.emptyList(), false);
    }

    public Todo(String description, TaskPriority priority, List<String> tags) {
        this(description, priority, tags, false);
    }

    public Todo(String description, TaskPriority priority, List<String> tags, boolean isDone) {
        super(description, priority, tags, isDone);
    }

    /**
     * Returns false as a todo has no date.
     * @param date A date.
     * @return false.
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
