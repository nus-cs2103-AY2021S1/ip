import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueDate;

    public Deadline(String name, LocalDateTime date, String dueDate) {
        super(name,date);
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
    }

    public String getdueDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.getdueDate() + ")";
    }
}
