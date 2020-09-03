import java.time.LocalDateTime;

public class Deadline extends Task {
    /** Deadline of the task. */
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
            this.by = Parser.getLocalDateTimeBy(by);
        } catch (Exception e) {
            throw new DukeException("invalidDeadlineDateTime");
        }
    }

    @Override
    public String toString() {
        String datetime = Parser.getDateTime(by);
        String day = Parser.getDay(by);
        return "[D]" + super.toString() + " (by: " + day + ", " + datetime + ")";
    }
}
