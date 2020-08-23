package exceptions;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException(){
        super("OOPS, this deadline activity is invalid");
    }
}
