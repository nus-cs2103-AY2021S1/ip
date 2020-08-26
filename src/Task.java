import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    String description;
    boolean isDone;
    String type = "";
    LocalDateTime deadline = null;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type;
    }

    public String getDeadline() {
        return this.deadline == null
                ? null
                : this.deadline.toString();
    }

    public String formatDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Task taskDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}