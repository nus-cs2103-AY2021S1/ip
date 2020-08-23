import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        LocalDate dateBy = null;
        try {
            dateBy = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            try {
                dateBy = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException exc) {
                throw new DukeException("Deadline timing cannot be parsed");
            }
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