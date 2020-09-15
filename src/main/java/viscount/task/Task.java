package viscount.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public abstract class Task {
    /**
     * Every task has a:
     * 1) task type
     * 2) description
     * 3) checkbox to indicate if it is done
     */
    protected TaskType taskType;
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a new task.
     *
     * @param taskType Type of the task created.
     * @param description Description of the task created.
     * @param isDone Represents if the task is done.
     */
    public Task(TaskType taskType, String description, boolean isDone) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns tick or cross symbols depending on whether the task is done.
     *
     * @return Tick or cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
        //return tick or X symbols
    }

    /**
     * Gets the type of the task.
     *
     * @return Type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a boolean representing whether the task is done.
     * @return True if the task is marked as done, and false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets whether the task is done.
     *
     * @param isDone New state of whether the task is marked as done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the description of the task.
     *
     * @param newDescription New description of task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Sets the date time field of the task.
     *
     * @param newDateTime New date time of the task.
     * @throws UnsupportedOperationException If method is called on tasks with no date time field.
     */
    public abstract void setDateTime(LocalDateTime newDateTime) throws UnsupportedOperationException;

    /**
     * Returns whether the task has a date time field.
     *
     * @return True if the task has a date time field, and false otherwise.
     */
    public abstract boolean hasDateTime();

    /**
     * Gets the date time of the task.
     *
     * @return Date time of time task.
     * @throws UnsupportedOperationException If method is called on tasks with no date time field.
     */
    public abstract LocalDateTime getDateTime() throws UnsupportedOperationException;

    /**
     * Gives a task data representation of the task in String format.
     *
     * @return Task data representation of the task.
     */
    public abstract String toTaskData();
}
