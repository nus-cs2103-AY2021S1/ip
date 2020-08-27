package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions that uses the wrong length.
 */
public class InvalidInstructionLengthException extends DukeException {
    public InvalidInstructionLengthException() {
        super("The length of the Instruction is wrong!", InvalidInstructionLengthException.class.getName());
    }
}
