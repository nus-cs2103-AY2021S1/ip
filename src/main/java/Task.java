public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "Done" : "Not Done"); //return tick or X symbols
    }
}
