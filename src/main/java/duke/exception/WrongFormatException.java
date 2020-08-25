package duke.exception;

/**
 * Encapsulates wrong format exceptions. These exceptions are thrown when the user enters a command with an invalid
 * format (the first word of the command is a valid command word but an error lies elsewhere in the command).
 */
public class WrongFormatException extends DukeException {

    /** The command type as specified by the first word of the command that the user entered */
    String commandName;

    /**
     * Creates and initializes a WrongFormatException object.
     *
     * @param commandName The command type as specified by the first word of the command that the user entered.
     */
    public WrongFormatException(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Returns an error message. Informs the user of the valid format of the command.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " The " + commandName + " " +
                "command has to be followed by a\n";
    }
}
