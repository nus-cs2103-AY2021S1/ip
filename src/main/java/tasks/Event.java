package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.DataException;

/**
 * Events have an additional event time (a {@code LocalDate} specified with /at).
 */
public class Event extends Task {

    // event held at this time
    private final LocalDate at;

    /**
     * Constructs a new Deadline.
     * @param desc description of the event
     * @param at event time - event is held at this time
     * @throws DataException if the event time or description is blank or invalid
     */
    public Event(String desc, String at) throws DataException {
        super(desc);
        if (at.isBlank()) {
            throw new DataException("Event Time", "Cannot be blank");
        }
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DataException("Event Time", "Parse error - " + e.getMessage());
        }
    }

    @Override
    protected char getTaskType() {
        return 'E';
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String getParentCommand() {
        return "event " + getDescription() + " /at " + at;
    }
}
