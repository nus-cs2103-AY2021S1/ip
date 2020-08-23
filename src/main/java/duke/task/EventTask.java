package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents an Event Task at a specific timePeriod.
 */
public class EventTask extends Task {

    private final LocalDate timePeriod; // YYYY-MM-DD

    /**
     * Initializes a new EventTask.
     *
     * @param description The description of the EventTask.
     * @param timePeriod  The timePeriod when the Event will occur. It should be in YYYY-MM-DD form.
     * @throws DukeException If the date format in timePeriod is wrong.
     */
    public EventTask(String description, String timePeriod) throws DukeException {
        super(description);
        try {
            this.timePeriod = LocalDate.parse(timePeriod);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date format should be YYYY-MM-DD");
        }
    }

    /**
     * Fully initialize an EventTask.
     *
     * @param description The description of the EventTask.
     * @param isDone      Indicates whether the EventTask has been done.
     * @param timePeriod  The timePeriod in LocalDate form when the EventTask will occur.
     */
    public EventTask(String description, boolean isDone, LocalDate timePeriod) {
        super(description, isDone);
        this.timePeriod = timePeriod;
    }

    @Override
    public EventTask markAsDone() {
        return new EventTask(description, true, timePeriod);
    }

    @Override
    public String getData() {
        return "E|" + super.getData() + "|" + timePeriod.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod
                .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
