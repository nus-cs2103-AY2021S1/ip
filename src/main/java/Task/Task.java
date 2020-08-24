package main.java.Task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(){}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    private String getStatus() {
        return isDone? "[✓]": "[✗]";
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }
}
