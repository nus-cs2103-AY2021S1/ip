package duke.task;

import duke.Priority;

/**
 * Encapsulates a task with a description and a completion status.
 */
public class Task {

    /**  Priority of the task. */
    private final Priority priority;

    /** Description of the task. */
    private final String description;

    /** Completion status of the task. */
    private boolean isDone;

    /**
     * Creates a new task from a description.
     *
     * @param priority  Priority of the task.
     * @param description Description of the task.
     */
    public Task(Priority priority, String description) {
        this.priority = priority;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new task from a description and completion status.
     *
     * @param priority Priority of the task.
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     */
    public Task(Priority priority, String description, boolean isDone) {
        this.priority = priority;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the priority of the task.
     *
     * @return Priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Gets the priority label of the task.
     *
     * @return String describing the priority of the task.
     */
    private String getPriorityLabel() {
        switch (this.priority) {
        case LOW:
            return "L";
        case MEDIUM:
            return "M";
        case HIGH:
            return "H";
        default:
            return "";
        }
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task using UTF-8 encoding.
     * Either a tick or a cross symbol.
     *
     * @return Status icon.
     */
    private String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets data from the task to be saved in storage.
     *
     * @return String representing the data of the task.
     */
    public String getData() {
        int statusNumber = this.getStatusIcon().equals("\u2713")
                ? 1
                : 0;
        return this.getPriorityLabel() + " / " + statusNumber + " / " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[" + this.getPriorityLabel() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
