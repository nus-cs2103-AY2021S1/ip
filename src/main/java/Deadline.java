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

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toStore() {
        String div = " | ";
        return "D" + div + (isDone ? "1" : "0") + div + description + div + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (byDate == null ? by : byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }
}
