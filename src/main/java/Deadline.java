import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    
    @Override
    public String writeMessage() {
        String done = "";
        if (this.isDone) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("D | %s | %s | %s", done, this.description, this.deadline);
    }

    @Override
    public String toString() {
            String str = " (by: ";
            str += deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            return "[D][" + (this.isDone ? "✓" : "✗") + "] " + this.description + str + ")";
    }
}
