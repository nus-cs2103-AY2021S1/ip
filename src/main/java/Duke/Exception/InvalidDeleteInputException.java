package Duke.Exception;

public class InvalidDeleteInputException extends DukeException {

    public InvalidDeleteInputException() {
        super("OOPS!!! Invalid input after delete command. Keep index within list range. (Example format: delete 1)");
    }
}
