import java.time.LocalDate;

public class Task {

    private String taskName;
    private boolean isDone;
    private boolean hasFormattedDate;
    protected LocalDate localDate;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        hasFormattedDate = false;
    }

    public Task(String taskName, boolean hasFormattedDate, LocalDate localDate) {
        this.taskName = taskName;
        this.isDone = false;
        this.hasFormattedDate = true;
        this.localDate = localDate;
    }

    public boolean hasDate() {
        return hasFormattedDate;
    }

    public LocalDate getDate() {
        return hasDate()
                ? localDate
                : null;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + "\u2713" + "] " + taskName;
        } else {
            return "[" + "\u2717" + "] " + taskName;
        }
    }

    public String getSaveFormat() {
        String done = isDone ? "1" : "0";
        return done + " | " + taskName;
    }
}
