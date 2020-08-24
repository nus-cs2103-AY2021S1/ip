package duke.exceptions;

/** Thrown to indicate that the user input an event without the date or time. */
public class EmptyEventDateException extends DukeException {

    /** Constructs an EmptyEventDateException with the relevant detail message. */
    public EmptyEventDateException() {
        super("OOPS! The date / time of event cannot be empty!");
    }
}
