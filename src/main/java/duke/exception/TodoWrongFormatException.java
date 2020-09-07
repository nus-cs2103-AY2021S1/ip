package duke.exception;

import duke.command.AddTodoCommand;

/**
 * Encapsulates a to-do wrong format exception. These exceptions are thrown when the user enters a to-do command with
 * an invalid format (the first word of the command is a valid command word but an error lies elsewhere in the
 * command).
 */
public class TodoWrongFormatException extends WrongFormatException {

    private static final String correctFormatDescription = "description of a task.";

    /**
     * Creates and initializes a TodoWrongFormatException object.
     */
    public TodoWrongFormatException() {
        super(AddTodoCommand.COMMAND_WORD, correctFormatDescription);
    }
}
