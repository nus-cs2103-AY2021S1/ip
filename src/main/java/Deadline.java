import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        LocalDate dateBy;
        try {
            dateBy = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        this.by = dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        return "D" + separator + isDone + separator + super.description + separator + by + "\n";
    }
}