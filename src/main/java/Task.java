public class Task {
    protected final String taskDescription;
    private boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public void setTaskAsDone()
    {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }
}