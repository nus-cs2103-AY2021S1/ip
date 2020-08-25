package viscount.task;

import java.time.LocalDateTime;

public abstract class Task {
    protected TaskType taskType;
    protected String description;
    protected boolean isDone;

    public Task(TaskType taskType, String description, boolean isDone) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
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

    public abstract boolean hasDateTime();
    public abstract LocalDateTime getDateTime();
    public abstract String toTaskData();
}