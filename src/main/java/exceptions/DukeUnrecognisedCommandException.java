package exceptions;

public class DukeUnrecognisedCommandException extends DukeException {

    private final String command;

    public DukeUnrecognisedCommandException(String message, String command) {
        super(message);
        this.command = command;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + command;
    }
}
