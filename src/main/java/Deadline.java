import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends DatedTask {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone, by);
    }

    @Override
    public String toSave() {
        return "D | " +  super.toSave();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
