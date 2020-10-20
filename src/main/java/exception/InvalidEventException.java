package exception;

public class InvalidEventException extends DukeErrorException {

    public InvalidEventException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: event <event description> /at <event date>";
    }
}
