package duke.exception;

/** An exception when there is no keyword following a find command. */
public class NoKeywordException extends DukeException {

    /** Constructs a @NoKeywordException. */
    public NoKeywordException() {
        super("OOPS. You need to input a keyword following \"find\".");
    }
}