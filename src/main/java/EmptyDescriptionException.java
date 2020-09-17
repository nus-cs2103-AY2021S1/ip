/**
 * Encapsulates an EmptyDescriptionException object, thrown when the description of a todo,
 * deadline, event or find command is empty. 
 */
public class EmptyDescriptionException extends DukeException {

    EmptyDescriptionException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }

}
