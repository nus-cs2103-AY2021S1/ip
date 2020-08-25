import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;

    Deadline(String description, String date) throws DateTimeParseException {
        super(description);
        parseDate(date);
    }

    void parseDate(String date) throws DateTimeParseException {
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        String formattedDate = this.date.format(formatter);
        return "[D] " + super.toString() + " (by: " + formattedDate + ")";
    }
}
