package Task;

public class Task {
    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void setCompleted() {
        this.completed = true;
    }
}
