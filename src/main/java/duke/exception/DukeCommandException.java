package duke.exception;

/**
 * An indication of command line errors due to
 * command line format issues and empty lists.
 */
public class DukeCommandException extends DukeException{

    public DukeCommandException(String message) {
        super(message);
    }
}