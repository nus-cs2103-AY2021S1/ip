package rogue.model.task;

/**
 * Represents a task that can be tracked by Rogue.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param description   The task description.
     * @param isDone        Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Displays an icon based on completion status of a {@code Task}.
     *
     * @return A tick if completed, or a cross otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a {@code Task} as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Retrieves print-friendly summary of a {@code Task}.
     * 1 indicates a completed {@code Task}, and 0 an incomplete {@code Task}.
     *
     * @return Print-friendly summary of task
     */
    public String summarize() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
