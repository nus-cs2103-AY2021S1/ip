public class Task {
    protected String taskName;
    protected boolean isCompleted;

    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.isCompleted;
    }

    public void markAsComplete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String task = "[";
        if (isCompleted) {
            task += "done";
            // task += "\u2713";
        } else {
            task += "not done";
            // task += "\u274C";
        }
        task += "] " + taskName;
        return task;
    }
}
