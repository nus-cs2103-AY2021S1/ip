package duke;

public class Task {
    protected boolean isDone;
    protected String description;
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }
}
