import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
    }

    public String dateFormatted() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static void invalidInput(String error) {
        String errorMsg = "Error: " + error;
        System.out.println(errorMsg);
    }

    @Override
    public String toString() {
        return "[" + this.isDone + "]" + this.description;
    }
}
