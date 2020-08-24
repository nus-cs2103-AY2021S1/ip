import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate byDate;

    public Deadline(String description, String byDate) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date detected! Please enter date as yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        if (super.isDone) return "D | 1 | " + description + " | " + byDate;
        else return "D | 0 | " + description + " | " + byDate;
    }
}