package tasks;

import exceptions.DataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    // event held at this time
    private final LocalDate at;

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
}
