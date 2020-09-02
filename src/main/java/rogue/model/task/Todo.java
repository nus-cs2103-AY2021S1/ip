package rogue.model.task;

/**
 * Represents a task that can be tracked by Rogue.
 *
 * A todo is any general task that has to be done.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo}.
     *
     * @param description   The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo}.
     *
     * @param description   The task description.
     * @param isDone        Whether the task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String summarize() {
        return String.format("T | %s", super.summarize());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
