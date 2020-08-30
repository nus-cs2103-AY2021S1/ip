package duke.exception;

/** An exception that occurs when a given endDate in an event occurs before its startDate. */
public class InvalidEndDate extends DukeException {
    public InvalidEndDate() {
        super("OOPS. Your end date occurs before the start date.");
    }
}
