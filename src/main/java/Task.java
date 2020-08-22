import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    protected boolean isDone;
    protected final String name;
    protected LocalDateTime createdDateTime;
    protected DateTimeFormatter formatter;

    protected Task(String name, LocalDateTime time) {
        this.isDone = false;
        this.name = name;
        this.createdDateTime = time;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public String getName() {
        return this.name;
    }

    public String getDateTime() {
        return createdDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public void complete() {
        this.isDone = true;
    }

    protected String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getName() + " [created on " + this.getDateTime()
                + "] ";
    }
}
