package exception;

public class InvalidDeadlineException extends DukeErrorException {

    public InvalidDeadlineException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: deadline <deadline description> /by <deadline date>";
    }
}
