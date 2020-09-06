package luke.task;

public abstract class Task {
    protected TaskType taskType;
    protected String description;
    protected boolean isDone;

    public Task(TaskType taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = false;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true; //mark the progress as done
    }

    public String toDataString() {
        int isDone = this.isDone ? 1 : 0;
        return String.format("%d|%s", isDone, this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
