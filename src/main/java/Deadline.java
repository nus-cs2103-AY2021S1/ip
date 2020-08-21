import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends DatedTask {
    public Deadline(String name, LocalDate deadline) {
        super(name, deadline);
    }

    public Deadline(String name, boolean completed, LocalDate deadline) {
        super(name, completed, deadline);
    }

    @Override
    public String format() {
        return "D" + SAVE_DELIMITER + super.format();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
