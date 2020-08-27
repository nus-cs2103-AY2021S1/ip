package duke.exception;

/**
 * Represents an Invalid Instruction Exception.
 * Apart from predefined fields in <code>DukeException</code>,
 * <code>InvalidInstructionException</code> describes errors about the input instruction by the user.
 * It provides an additional <code>String</code> instructionType to feedback to the user
 * on the proper type of instruction to be used.
 */
public class InvalidInstructionException extends DukeException {

    public InvalidInstructionException(String instructionType) {
        super("Please key in a valid instruction!",
                InvalidInstructionException.class.getName(), "Source - " + instructionType);
    }
}