package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getPlainText() {
        char className = this.getClass().getSimpleName().charAt(0);
        String completionStatus = isDone? "1" : "0";
        return className + " | " + completionStatus + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}