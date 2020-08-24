package duke;

public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
            return isDone ? "X" : " ";
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String fileFormat() {
        String indicateDone = isDone ? "1" : "0";
        return indicateDone + " , " + description + " , ";
    }
}
