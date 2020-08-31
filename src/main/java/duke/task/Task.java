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

    /**
     * Create an undone task with the specified description
     * @param description A description of this task
     */
    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

    /**
     * Create a task with the specified description, and additionally
     * specifying the completion status of the task
     * @param isCompleted A boolean to indicate the completion status of the task
     * @param description A description of this task
     */
    public Task(boolean isCompleted, String description) {
        this.isCompleted = isCompleted;
        this.description = description;
    }

    /**
     * @return A Symbol representing the completion status of this task
     */
    public String isCompletedSymbol() {
        return this.isCompleted ? SYMBOL_DONE : SYMBOL_UNDONE;
    }

    /**
     * @return The completion status of this task
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * @param isCompleted Set the completion status of this task
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * @return The description of this task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description Set the description of this task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return A csv representation of this task
     */
    public abstract String toCsv();

    /**
     * Two Tasks are equivalent if they have the same description, and have the
     * same completion status
     * @param obj The other object to compare to
     * @return true if they are equivalent. Otherwise, false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return isCompleted() == task.isCompleted()
                && getDescription().equals(task.getDescription());
    }

    /**
     * @return The hashcode of the Task's completion status and description
     */
    @Override
    public int hashCode() {
        return Objects.hash(isCompleted(), getDescription());
    }
}
