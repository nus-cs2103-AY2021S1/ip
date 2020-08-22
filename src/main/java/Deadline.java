import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try{
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            byDate = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (byDate == null ? by : byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }
}
