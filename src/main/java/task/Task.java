package task;

public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void completeTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (getStatus() ? "[\u2713] " : "[\u2717] ") + getTaskDescription();
    }
}
