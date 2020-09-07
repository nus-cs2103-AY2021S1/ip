package duke.exception;

/**
 * Encapsulates wrong format exceptions. These exceptions are thrown when the user enters a command with an invalid
 * format (the first word of the command is a valid command word but an error lies elsewhere in the command).
 */
public class WrongFormatException extends DukeException {

    /**
     * Creates and initializes a WrongFormatException object.
     *
     * @param commandWord The command type as specified by the first word of the command that the user entered.
     */
    public WrongFormatException(String commandWord, String correctFormatDescription) {
        super(" The " + commandWord + " command has to be followed by a\n" + correctFormatDescription);
    }
}
