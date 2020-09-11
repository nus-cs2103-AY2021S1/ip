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

    public TaskType getTaskType() {
        return taskType;
    }
    public String getDescription() {
        return description;
    }
    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public abstract void setDateTime(LocalDateTime newDateTime) throws UnsupportedOperationException;

    public abstract boolean hasDateTime();
    public abstract LocalDateTime getDateTime();
    public abstract String toTaskData();
}
