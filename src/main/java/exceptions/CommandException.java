package exceptions;

/**
 * Exception representing an error parsing the command strings.
 */
public class CommandException extends IPException {

    private final String command;
    private final String reason;

    /**
     * Creates a new exception.
     * @param command the command that could not be parsed
     * @param reason the reason why the command could not be parsed
     */
    public CommandException(String command, String reason) {
        super(reason);
        this.command = command;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Sorry, I can't process this command: " + command + "\nReason: " + reason;
    }
}
