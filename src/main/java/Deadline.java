import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDate deadlineTime;

    Deadline(String taskName, String deadlineTime) throws DukeException {
        super (taskName);
        // ISO_LOCAL_DATE format, no conversion needed (yyyy-mm-dd)
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write down our dates in the standard format, yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[DEADLINE]" + super.toString() + " | by: " + deadlineTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
