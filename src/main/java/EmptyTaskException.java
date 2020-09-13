/**
 * Defines a Duke-type exception that is thrown due to an empty task input.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class EmptyTaskException extends DukeException {

    EmptyTaskException(String message) {
        super(message);
    }

}
