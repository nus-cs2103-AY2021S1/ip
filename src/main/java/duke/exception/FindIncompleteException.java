package duke.exception;

public class FindIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify a keyword of the task that you "
                + "\n" + " want to find.";
    }
}
