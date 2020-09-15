package ekud.exceptions;

public class DukeUnrecognisedCommandException extends DukeException {

    private final String command;

    /**
     * Instantiates a new ekud.Duke unrecognised command exception.
     *
     * @param message the message
     * @param command the command which was not recognised
     */
    public DukeUnrecognisedCommandException(String message, String command) {
        super(message);
        this.command = command;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + command;
    }
}
