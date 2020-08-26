package duke.exceptions;

public class EmptyFindException extends DukeException {
    public EmptyFindException() {
        super("Please input a search term");
    }
}
