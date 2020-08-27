package duke.exception;

/**
 * Represents a Missing Field Exception.
 * Apart from predefined fields in <code>DukeException</code>,
 * <code>MissingFieldException</code> describes errors about the input by the user.
 * It provides an additional <code>String</code> missingFields to feedback to the user
 * what the user missed out when inputting instructions.
 */
public class MissingFieldException extends DukeException {

    public MissingFieldException(String missingFields) {
        super("Please key in the missing fields", MissingFieldException.class.getName(), "Fields Missing - " + missingFields);
    }
}