import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
//    protected String by;
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return stringifyBy();
    }

    public String stringifyBy() {
        return by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + stringifyBy() + ")";
    }
}
