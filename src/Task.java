import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;
    protected String duration;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public Task(String description, LocalDate date, String duration) {
        this.description = description;
        this.date = date;
        this.duration = duration;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}