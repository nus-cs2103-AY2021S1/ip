import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = LocalDate.parse("0001-01-01");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public LocalDate getDate() {
        return date;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
