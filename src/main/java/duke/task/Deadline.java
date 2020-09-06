package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * This is a Deadline Task.
 * Keeps a description as well as a date for which the Deadline is due.
 * Also stores a LocalDate object of the date, and outputs in the format MMM dd yyyy.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate byLocalDate;

    /**
     * Constructs a new Deadline Task.
     * @param description Description of Task.
     * @param by Date due for the Deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            this.byLocalDate = LocalDate.parse(by);
        } catch (Exception e) {
            throw new DukeException("Date is not in YYYY-MM-DD format");
        }
    }

    /**
     * Returns the letter D for writing to hard disk file.
     */
    @Override
    public String getSaveSymbol() {
        return Task.DEADLINE_SAVE_SYMBOL;
    }

    /**
     * Returns Optional containing the deadline.
     */
    @Override
    public Optional<String> getDate() {
        return Optional.of(this.by);
    }

    @Override
    public String toString() {
        return "[" + Task.DEADLINE_SAVE_SYMBOL + "]" + super.toString() + " (by: " +
                byLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
