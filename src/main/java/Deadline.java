import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String STRING_FORMAT = "[D][%s] %s (by: %s)";

    protected LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    
    @Override
    public boolean hasDateTime() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(Deadline.STRING_FORMAT, getStatusIcon(), description, 
                dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")));
    }
}
