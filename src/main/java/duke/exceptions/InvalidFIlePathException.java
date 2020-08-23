package duke.exceptions;

public class InvalidFIlePathException extends DukeException {

    public InvalidFIlePathException() {
        super("OOPS! File paths must end with .txt");
    }
}
