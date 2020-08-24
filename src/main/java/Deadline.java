import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String desc, String by) {
        super(desc);
        String cleaned = by.replaceAll("\\s+", "");
        this.by = cleaned;
        try {
            this.date = LocalDate.parse(cleaned);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    @Override
    public String toString() {
        if (date != null) {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + by + ")";
        }
    }
}
