import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDate() + ")";
    }

}
