import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * represents a task
 */
public class Task {
    private String type;
    private String todo;
    private LocalDate date;
    private boolean isDone;

    Task(String type, String todo, LocalDate date, boolean isDone) {
        this.type = type;
        this.todo = todo;
        this.date = date;
        this.isDone = isDone;
    }

    public void setStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public String toDateStr(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toDbDateStr(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String toString() {
        String description = String.format("[%s][%s] %s", type, isDone ? "✓" : "✗", todo);

        if (type.equals("D") || type.equals("E")) {
            description += String.format(" (%s: %s)", type.equals("D") ? "by" : "at", toDateStr(date));
        }

        return description;
    }

    public String toDbString() {
        String log = String.format("%s|%s|%s", type, isDone, todo);

        if (type.equals("D") || type.equals("E")) {
            log += String.format("|%s", toDbDateStr(date));
        }

        return log;
    }
}
