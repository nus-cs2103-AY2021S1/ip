package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents tasks that start at a specific date.
 */
public class Event extends Task {
    private char type = 'E';
    private LocalDate date;
    private final String AT = "to be digested at: ";

    /**
     * Creates an Event instance.
     *
     * @param task A string containing task details.
     * @param date A string containing start date of event.
     * @throws DukeException If invalid date format.
     */
    public Event(String task, String date) throws DukeException {
        super(task);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time format should be yyyy/mm/dd or yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s%s", type, super.toString(), AT,
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s | %s", type, super.saveToString(), date);
    }
}
