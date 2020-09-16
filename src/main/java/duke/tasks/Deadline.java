package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents tasks that need to be done before a specific date.
 */
public class Deadline extends Task{
    private char type = 'D';
    private LocalDate date;
    private final String BY = "to be digested by: ";

    /**
     * Creates a Deadline instance.
     *
     * @param task A string containing task details.
     * @param date A string containing date of deadline.
     * @throws DukeException If invalid date format.
     */
    public Deadline(String task, String date) throws DukeException {
        super(task);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time format should be yyyy/mm/dd or yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%c]%s %s%s", type, super.toString(), BY,
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s | %s", type, super.saveToString(), date);
    }
}
