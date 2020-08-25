package duke.tasks;

public class Task {
    private boolean hasCompleted;
    private String taskName;

    public Task(String name) {
        this.taskName = name;
        this.hasCompleted = false;
    }

    protected String getStatus() {
        return (this.hasCompleted ? "\u2713" : "\u2718");
    }

    public void markDone() {
        this.hasCompleted = true;
    }

    protected String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.taskName;
    }

}
