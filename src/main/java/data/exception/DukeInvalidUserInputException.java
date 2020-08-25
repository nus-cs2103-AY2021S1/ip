package data.exception;

/**
 * Signals that a given user input is invalid and does not fulfill certain constraints.
 */
public class DukeInvalidUserInputException extends DukeException{
    /**
     * @param msg that contains information related to the failed constraints.
     */
    public DukeInvalidUserInputException(String msg) {
        super(msg);
    }
}
