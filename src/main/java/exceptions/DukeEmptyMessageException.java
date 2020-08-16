package exceptions;

public class DukeEmptyMessageException extends DukeException {

    public DukeEmptyMessageException(String message) {
        super("Oops! " + message + " cannot have an empty message!");
    }
}
