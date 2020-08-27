package duke.exception;

/**
 * Represents an Invalid Format Exception.
 * Apart from predefined fields in <code>DukeException</code>,
 * <code>InvalidFormatException</code> describes errors about the input format by the user.
 * It provides an additional <code>String</code> instructionFormat to feedback to the user
 * on the proper format to be used.
 */
public class InvalidFormatException extends DukeException {

    public InvalidFormatException(String instructionFormat) {
        super("Please use the correct format for your instructions!",
                InvalidFormatException.class.getName(), "Format - " + instructionFormat);
    }
}
