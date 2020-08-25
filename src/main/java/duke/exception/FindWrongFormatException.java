package duke.exception;

public class FindWrongFormatException extends WrongFormatException {

    public FindWrongFormatException() {
        super("find");
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "word / words that you would like to search for in\nthe task list.";
    }
}
