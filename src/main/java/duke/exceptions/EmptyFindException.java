package duke.exceptions;

public class EmptyFindException extends DukeException{
    @Override
    public String getMessage() {
        return "Please key in a keyword to find";
    }
}
