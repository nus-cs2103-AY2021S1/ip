package duke;

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

    public EventTask(String description, boolean isDone, LocalDate dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public EventTask markAsDone() {
        return new EventTask(description, true, dateTime);
    }

    @Override
    public String getData() {
        return "E|" + super.getData() + "|" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
