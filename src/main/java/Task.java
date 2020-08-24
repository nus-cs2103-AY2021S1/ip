public abstract class Task {
    private final String description;
    private final TaskType taskType;

    private boolean isDone;
    private final String time;

    protected Task(String description, boolean isDone, TaskType taskType, String time) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
        this.time = time;
    }

    private String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected boolean isDone() {
        return isDone;
    }

    protected String getStatus() {
        return isDone ? "Done" : "Not done";
    }

    protected String getTaskName() {
        return taskType.toString().toUpperCase();
    }

    protected String getDescription() {
        return description;
    }

    protected String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}