package duke.exception;

/**
 * Represents an exception when user does not provide any keyword/keyphrase.
 */
public class NoFindContentException extends DukeException{

    /**
     * Constructs a NoFindContentException.
     */
    public NoFindContentException() {
        super("OOPS!!! Please input a keyword/keyphrase you want to search for.");
    }
}
