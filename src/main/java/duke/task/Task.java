package duke.task;

import java.util.Objects;

/**
 * A task is a is an item that has a description and can be marked as completed
 */
public abstract class Task {

    private static final String SYMBOL_DONE = "✓";
    private static final String SYMBOL_UNDONE = "✗";

    private boolean isCompleted;
    private String description;

    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

    public Task(boolean isCompleted, String description) {
        this.isCompleted = isCompleted;
        this.description = description;
    }

    public String isCompletedSymbol() {
        return this.isCompleted ? SYMBOL_DONE : SYMBOL_UNDONE;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String toCsv();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task task = (Task) obj;
        return isCompleted() == task.isCompleted() &&
                getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCompleted(), getDescription());
    }
}
