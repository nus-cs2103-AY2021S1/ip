import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDate dateTime; // YYYY-MM-DD

    public DeadlineTask(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date format should be YYYY-MM-DD");
        }
    }

    private DeadlineTask(String description, boolean isDone, LocalDate dateTime) throws DukeException {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public DeadlineTask markAsDone() throws DukeException {
        return new DeadlineTask(description, true, dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
