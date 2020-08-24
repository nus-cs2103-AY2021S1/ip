package duke.exceptions;

/** Thrown to indicate that the file path is invalid. */
public class InvalidFilePathException extends DukeException {

    /** Constructs an InvalidFilePathException with the relevant detail message. */
    public InvalidFilePathException() {
        super("OOPS! File paths must end with .txt");
    }
}
