package duke.task;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, TaskPriority priority, List<String> tags) {
        this(description, false, priority, tags);
    }

    public Todo(String description, boolean isDone) {
        this(description, isDone, TaskPriority.NONE, Collections.emptyList());
    }

    public Todo(String description, boolean isDone, TaskPriority priority, List<String> tags) {
        super(description, isDone, priority, tags);
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
