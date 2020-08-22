import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private final LocalDate dateTime; // YYYY-MM-DD

    public EventTask(String description, String timePeriod) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDate.parse(timePeriod);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date format should be YYYY-MM-DD");
        }
    }

    public EventTask(String description, boolean isDone, LocalDate dateTime) throws DukeException {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public EventTask markAsDone() throws DukeException {
        return new EventTask(description, true, dateTime);
    }

    @Override
    public String printData() {
        return "E|" + super.printData() + "|" + dateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
