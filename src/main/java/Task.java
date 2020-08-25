public class Task {
    // 3 types of tasks: todos, deadline, event
    // use polymorphism to store all tasks in a DS containing Task obj
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    public void markDone() {
        isDone = true;
    }

    public String toString() {
        return getStatusIcon() + description;
    }
}
