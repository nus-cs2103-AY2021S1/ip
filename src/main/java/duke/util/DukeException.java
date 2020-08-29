package duke.util;

/**
 * Since Duke has specific keywords or command formats required from
 * the user input to function as intended, and user inputs can deviate
 * from expected formats due to an array of errors i.e. number format error,
 * wrong command error, non-existing command error, command format error,
 * the DukeException helps to standardize all Duke related errors.
 */
public class DukeException extends Exception {

    /**
     * Constructor which prepends a Duke error signature
     * to the given message for standardization.
     * @param message the error message.
     */
    public DukeException(String message) {
        super("!!Whoops!! " + message);
    }
}
