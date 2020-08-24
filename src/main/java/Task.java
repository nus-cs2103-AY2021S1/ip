import java.time.LocalDate;

public class Task implements java.io.Serializable {
    String text;
    boolean done;
    LocalDate date;

    public Task(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    public String toString() {
        return "[" + (done ? "✓" : "✗") + "] " + text;
    }
}
