package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions that have one or more fields missing.
 */
public class MissingFieldException extends DukeException {
    public MissingFieldException() {
        super("One or more of the required fields are missing!", MissingFieldException.class.getName());
    }

    @Override
    public String guiString() {
        return "...nggghhhh...some field is missing in your instructions...\n"
                + "Duketama says I can nap till you find it...zzz...";
    }
}
