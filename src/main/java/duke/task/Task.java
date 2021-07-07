package duke.task;

import duke.Priority;

/**
 * Represents a Task
 */
public class Task {

    /** Description of task. */
    protected String description;
    /** Status of task. */
    protected boolean isDone;
    /** Priority of Task */
    protected Priority priority;

    /**
     * Constructs a new instance of a Task with attributes defined in parameters.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = null;
    }

    /**
     * Constructs a new instance of a Task with attributes defined in parameters.
     * @param description Description of task.
     * @param priority Priority of task.
     */
    public Task(String description, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    public boolean getCompletionStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPriority() {
        return this.priority == null ? "" : this.priority.toString();
    }

    public void addPriority(Priority priority) {
        this.priority = priority;
    }

    public void updatePriority(Priority priority) {
        this.priority = priority;
    }

    public void removePriority() {
        this.priority = null;
    }

    /**
     * Retrieves the status icon of task.
     * @return A string value of status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts task to a text format to be stored in data.
     * @return Returns a formatted task string.
     */
    public String parseTaskToText() {
        return "T" + " | " + (getCompletionStatus() ? "1" : "0") + " | " + getDescription()
                + " | " + getPriority();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
