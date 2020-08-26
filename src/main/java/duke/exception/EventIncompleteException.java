package duke.exception;

public class EventIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify the description of an event.";
    }
}
