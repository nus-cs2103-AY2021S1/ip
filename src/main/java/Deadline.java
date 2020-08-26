import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }
    
    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("D | %s | %s | %s", done, this.task, this.by);
    }

    @Override
    public String toString() {
            String str = " (by: ";
            str += by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            return "[D][" + (this.done ? "✓" : "✗") + "] " + this.task + str + ")";
    }
}
