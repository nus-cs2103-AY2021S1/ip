package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the command 'find' does not have a keyword.
 */
public class DukeEmptyFindException extends DukeException {

    public static final String ERROR_ALREADY_DONE = "Mhiss-ing keyword to find!";


    /**
     * DukeEmptyFindException constructor.
     */
    public DukeEmptyFindException() {
        super(ERROR_ALREADY_DONE);
    }
}
