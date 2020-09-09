package duke.exception;

import duke.command.AddEventCommand;

/**
 * Encapsulates an event wrong format exception. These exceptions are thrown when the user enters an event command with
 * an invalid format (the first word of the command is a valid command word but an error lies elsewhere in the
 * command).
 */
public class EventWrongFormatException extends WrongFormatException {

    private static final String correctFormatDescription = "description of a task in the following format:\nevent /at"
            + " time and/or place.";

    /**
     * Creates and initializes an EventWrongFormatException object.
     */
    public EventWrongFormatException() {
        super(AddEventCommand.COMMAND_WORDS.get(0), correctFormatDescription);
    }
}
