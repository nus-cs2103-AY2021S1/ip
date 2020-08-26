package duke.task;

import java.util.UUID;

/**
 * Wrapper class for all types of tasks.
 */
public class Task {

    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected String uniqueId;

    /**
     * Creates a brand new Task object.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task from existing data.
     * @param uniqueId Unique Id of the task.
     * @param isDone Task completion status.
     * @param description Task description.
     */
    public Task(String uniqueId, boolean isDone, String description) {
        this.uniqueId = uniqueId;
        this.isDone = isDone;
        this.description = description;
    }
    
    public void markDone() {
        isDone = true;
    }
    
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskType() {
        return taskType;
    }

    /**
     * Generates a unique id for the task. 
     */
    public void generateUniqueId() {
        this.uniqueId = UUID.randomUUID().toString();
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}