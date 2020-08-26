package duke.exception;

public class InvalidInstructionException extends DukeException {

    public InvalidInstructionException(String instructionType) {
        super("Please key in a valid instruction!",
                InvalidInstructionException.class.getName(), "Source - " + instructionType);
    }
}