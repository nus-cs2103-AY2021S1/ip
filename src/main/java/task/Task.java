package task;

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
        return (isDone ? "✓" : "✘"); //return \u2713 or \u2718 symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getType() {
        return "1"; // dummy type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDateInput() {
        return "No date available";
    }

    public String getTimeInput() {
        return "No time available";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
