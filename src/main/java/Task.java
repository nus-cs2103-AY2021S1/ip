import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }


    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public int getDataStatus() {
        return (isDone ? 1 : 0);
    }

    public String getData() {
        return "| " + getDataStatus() + " | " + this.description;
    }
}
