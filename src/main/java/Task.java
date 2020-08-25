public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return tick or cross symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }

}
