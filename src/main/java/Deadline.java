import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    public Deadline(String description, LocalDateTime dueDate) {
        super(description, dueDate);
    }
    public String writeToFile() {
        return "D|" + super.writeToFile() + "|" + super.dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by :" + super.getDateString() + ")";
    }
}
