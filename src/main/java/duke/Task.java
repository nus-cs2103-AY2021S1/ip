package duke;

public class Task {
    protected String taskname;
    protected boolean status;

    public Task(String taskname, boolean status) {
        this.taskname = taskname;
        this.status = status;
    }

    protected String getStatusIcon() {
        return (status ? "\u2713" : "\u2718");
    }

    protected void markAsDone() {
        this.status = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskname;
    }
}
