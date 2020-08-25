import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + by.format(format) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + (this.isDone == true ? '1' : '0') + " | " + this.taskDescription + "|" + this.by;
    }
}
