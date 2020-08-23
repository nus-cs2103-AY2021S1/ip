import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;
    private String byString;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        try{
            this.byString = by;
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.by = null;
        }
    }

    @Override
    public String toDataString() {
        if (super.isDone) return "D | 1 | " + description + " | " + byString;
        else return "D | 0 | " + description + " | " + byString;
    }

    @Override
    public String toString() {
        if (this.by != null) return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        else return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}