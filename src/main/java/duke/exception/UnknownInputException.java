package duke.exception;

public class UnknownInputException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Sorry, but I do not know what that means :(";
    }
}
