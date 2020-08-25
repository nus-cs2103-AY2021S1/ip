import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String symbol;
    protected String by;
    protected LocalDate date;
    protected LocalDateTime dateTime;

    public Task(String description) {
        this.description = description;
        isDone = false;
        by = "";
        date = null;
        dateTime = null;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDate() {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a"));
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return by;
        }
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + description);
    }
}
