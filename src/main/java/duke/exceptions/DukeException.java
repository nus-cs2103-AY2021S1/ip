package duke.exceptions;

public class DukeException extends RuntimeException {

    public DukeException(String message) {
        super(String.format("Gomen nasai~ %s\n", message));
    }
}
