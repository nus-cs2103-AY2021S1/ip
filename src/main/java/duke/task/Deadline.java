package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.exception.DukeException;

/**
 * This is a Deadline Task.
 * Keeps a description as well as a date for which the Deadline is due.
 * Also stores a LocalDate object of the date, and outputs in the format MMM dd yyyy.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate byLocalDate;

    /**
     * Constructor
     * @param description Description of Task.
     * @param by Date due for the Deadline.
     */
    private Deadline(String description, String by, LocalDate byLocalDate) {
        super(description);
        this.by = by;
        this.byLocalDate = byLocalDate;
    }

    /**
     * Invoked to create a new Deadline Task.
     * @param description Description of Task.
     * @param by Date due for the Deadline.
     */
    public static Deadline createDeadline(String description, String by) throws DukeException {
        try {
            LocalDate byLocalDate = LocalDate.parse(by);
            return new Deadline(description, by, byLocalDate);
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

    @Override
    public Optional<String> getFieldIdentifier() {
        return Optional.of(Task.DEADLINE_FIELD_IDENTIFIER);
    }

    /**
     * Returns Optional containing the deadline.
     */
    @Override
    public Optional<String> getDate() {
        return Optional.of(this.by);
    }

    @Override
    public Task duplicate() {
        Deadline duplicateDeadline = new Deadline(description, by, byLocalDate);
        if (this.isDone()) {
            duplicateDeadline.markAsDone();
        }

        return duplicateDeadline;
    }

    @Override
    public void setField(String fieldContent) throws DukeException {
        try {
            this.by = fieldContent;
            this.byLocalDate = LocalDate.parse(fieldContent);
        } catch (Exception e) {
            throw new DukeException("Date is not in YYYY-MM-DD format");
        }
    }

    @Override
    public String toString() {
        return "[" + Task.DEADLINE_SAVE_SYMBOL + "]" + super.toString() + " (by: "
                + byLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
