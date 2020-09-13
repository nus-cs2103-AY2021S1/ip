/**
 * Defines a Duke-type exception that is thrown due to an invalid keyword.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class InvalidKeyWordException extends DukeException {

    InvalidKeyWordException(String message) {
        super(message);
    }
}
