package duke.exception;

public class DeleteWrongFormatException extends WrongFormatException {

    public DeleteWrongFormatException() {
        super("delete");
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "single whitespace and an integer";
    }
}