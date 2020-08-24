import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    public Deadline(String description, LocalDateTime dueDate) {
        super(description, dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by :" + super.getDateString() + ")";
    }
}
