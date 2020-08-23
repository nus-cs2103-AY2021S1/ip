package duke.exception;

public class DoneWrongFormatException extends WrongFormatException {

    public DoneWrongFormatException() {
        super("done");
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "single whitespace and an integer";
    }
}
