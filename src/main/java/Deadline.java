import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Deadline(boolean isDone, String name, LocalDate date) {
        super(isDone, name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
