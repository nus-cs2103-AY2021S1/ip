package duke.task;

public abstract class Task {
    private String description;
    private TaskType taskType;
    private String time;

    private boolean isDone;

    protected Task(String description, boolean isDone, TaskType taskType, String time) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
        this.time = time;
    }

    private String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatus() {
        return isDone ? "Done" : "Not done";
    }

    public String getTaskName() {
        return taskType.toString().toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}