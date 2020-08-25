import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime localDate;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = by;
        this.localDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String writeSaveFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
