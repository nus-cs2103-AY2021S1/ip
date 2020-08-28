package duke.exceptions;

public class NullCommandContentException extends Exception {

    private String typeOfCommand;

    public NullCommandContentException(String err, String typeOfCommand) {
        super(err);
        this.typeOfCommand = typeOfCommand;
    }
    @Override
    public String getMessage() {
        return "💔OH NO~~ The description of " + this.typeOfCommand + " cannot be empty";
    }
}
