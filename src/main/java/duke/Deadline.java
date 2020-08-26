package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Creates a Deadline object.
     * @param description Description of the deadline.
     * @param date Date of the deadline.
     * @throws DukeException If date format is wrong.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);
        this.date = parseDeadlineDate(date);
    }
    /**
     * Creates a Deadline object with done/not done status.
     * @param isDone Whether the deadline is done.
     * @param description Description of the deadline.
     * @param date Date of the deadline.
     * @throws DukeException If date format is wrong.
     */
    public Deadline(boolean isDone, String description, String date) throws DukeException {
        super(isDone, description);
        this.date = parseDeadlineDate(date);
    }

    /**
     * Converts string date to LocalDate.
     * @param date String date input.
     * @return Converted LocalDate object.
     * @throws DukeException If date format is wrong.
     */
    private static LocalDate parseDeadlineDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS! Wrong Date/Time format! Type 'help' to see the correct format");
        }
    }

    @Override
    String getTaskDetailsForSave() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | "
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + String.format(" (by: %s)", formattedDate);
    }
}
