/**
 * Defines a Duke-type exception that is thrown when a task fails to be deleted.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class DeleteFailureException extends DukeException {

    DeleteFailureException(String message) {
        super(message);
    }

}
