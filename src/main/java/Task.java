import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone
                ? "✓"
                : "✘";
    }

    protected String printDate() {
        return this.date.getDayOfWeek() + ", " + this.date.getMonth() + " " +
                this.date.getDayOfMonth() + " " + this.date.getYear();
    }

    public void finishTask() { isDone = true; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }


}
