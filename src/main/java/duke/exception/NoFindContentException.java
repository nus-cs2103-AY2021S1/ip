package duke.exception;

public class NoFindContentException extends DukeException{

    public NoFindContentException() {
        super("OOPS!!! Please input a word/phrase you want to search for.");
    }
}
