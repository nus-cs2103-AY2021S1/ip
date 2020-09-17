package duke.exceptions;

public class NullCommandContentException extends DukeException {

    private String typeOfCommand;

    /**
     * Constructor for null command content exception.
     * @param err errorMessage
     * @param typeOfCommand command type
     */
    public NullCommandContentException(String err, String typeOfCommand) {
        super(err);
        this.typeOfCommand = typeOfCommand;
    }
    @Override
    public String getMessage() {
        return "💔OH NO~~ The description of " + this.typeOfCommand + " cannot be empty";
    }
}
