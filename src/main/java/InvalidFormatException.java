/**
 * Defines a Duke-type exception that is thrown when an input format is rejected.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class InvalidFormatException extends DukeException {

    InvalidFormatException(String message) {
        super(message);
    }

}
