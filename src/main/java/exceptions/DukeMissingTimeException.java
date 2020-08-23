package exceptions;

public class DukeMissingTimeException extends DukeException {

    public DukeMissingTimeException() {
        super("Sorry Boss! Please ensure that you have input the time");
    }
}
