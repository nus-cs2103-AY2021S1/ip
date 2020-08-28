package duke.exception;

/**
 * Exception for handling unsupported commands.
 */
public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException(String input) {
        super(input);
    }

    /**
     * Returns the error message containing the user input which caused the error. 
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke can't recognise your command -> " + input;
    }
}
