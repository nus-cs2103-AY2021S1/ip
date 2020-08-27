package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions that uses the wrong format.
 */
public class InvalidInstructionFormatException extends DukeException {
    public InvalidInstructionFormatException() {
        super("Instruction format is incorrect! Please check!", InvalidInstructionFormatException.class.getName());
    }
}
