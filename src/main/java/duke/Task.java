package duke;

public class Task {
    protected String taskTitle;
    protected boolean isDone;
    protected TaskTypes taskType;

    public Task(String taskTitle, Boolean isDone, TaskTypes taskType) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "✓" : "✗");
    }

    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    public String getTaskTitle() {
        return this.taskTitle;
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatus() + "] " + taskTitle;
    }

    public String writeToFile() {
        return this.taskType + " | " + getStatusNum() + " | " + taskTitle;
    }
}
