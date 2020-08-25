/**
 * Represents a deadline as a task with a date and/or time.
 */
public class Deadline extends Task {

    private String dateTime;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.dateTime = DateTimeHandler.parseDateTime(by);
    }

    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
