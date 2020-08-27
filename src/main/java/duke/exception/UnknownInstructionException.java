package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions that are unknown.
 */
public class UnknownInstructionException extends DukeException {
    public UnknownInstructionException() {
        super("Instruction is unknown! Please check!", UnknownInstructionException.class.getName());
    }
}
