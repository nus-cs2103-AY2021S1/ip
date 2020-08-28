//Adapted from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html
public class Task {
    protected final String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or cross symbols
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }
}
