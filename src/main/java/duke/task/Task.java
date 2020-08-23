package duke.task;

public class Task {
    protected final String description;
    protected boolean isDone;
    public final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return taskType + "[" + getStatusIcon() +"] " + description;
    }
}