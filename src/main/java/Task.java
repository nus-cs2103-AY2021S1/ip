import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getStatus() {
        return isDone;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}