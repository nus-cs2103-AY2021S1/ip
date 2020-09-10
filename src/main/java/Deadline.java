import java.time.LocalDateTime;

public class Deadline extends Task {
    /** Deadline of the task in String type. */
    protected String originalBy;

    /** Deadline of the task in LocalDateTime type. */
    protected LocalDateTime by;

    /**
     * Constructs new Deadline object.
     *
     * @param description Description of the Deadline task.
     * @param by Deadline of the task.
     * @throws DukeException If Deadline task format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.originalBy = by;
            this.by = Parser.getLocalDateTimeBy(by);
        } catch (Exception e) {
            throw new DukeException("invalidDeadlineDateTime");
        }
    }

    @Override
    public String toString() {
        String strBy = Parser.getStringBy(by);
        String day = Parser.getDay(by);
        return "[D]" + super.toString() + " (by: " + day + ", " + strBy + ")";
    }

    /**
     * Returns the Deadline task string to be written to the duke.txt storage file.
     *
     * @return Deadline task string.
     */
    public String toStorageString() {
        return "[D]" + super.toStorageString() + " (by: " + this.originalBy + ")";
    }
}
