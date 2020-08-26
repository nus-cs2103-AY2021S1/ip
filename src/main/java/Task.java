import java.time.LocalDate;

/**
 * Represents a task
 */
public class Task implements java.io.Serializable {
    String text;
    boolean done;
    LocalDate date;

    /**
     * @param text Task description
     * @param date Date of the task
     */
    public Task(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    /**
     * @return String representation of the task
     */
    public String toString() {
        return "[" + (done ? "✓" : "✗") + "] " + text;
    }
}
