import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate date;
    public Deadline(String description, String date) throws DukeException {
        super(description);
        this.date = parseDeadlineDate(date);
    }

    private static LocalDate parseDeadlineDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS! Wrong Date/Time format! Type 'help' to see the correct format");
        }
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + String.format(" (by: %s)", formattedDate);
    }
}
