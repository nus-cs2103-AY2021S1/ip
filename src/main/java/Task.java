import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() throws InvalidCommandException {
        if (isDone) {
            throw new InvalidCommandException("The task " + this + " has already been done.");
        }
        isDone = true;
    }

    public String output() {
        return " | " + getStatusIcon() + " | " + description;
    }

    public boolean happenOnDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
