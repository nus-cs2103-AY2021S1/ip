package duke.command;

import duke.exception.InvalidCommandException;

/**
 * Abstraction for a command parsed into Duke.
 */
public interface Command {
    /**
     * Performs the command and prints out a response indicating the outcome.
     *
     * @return Message detailing outcome of operation.
     * @throws InvalidCommandException If invalid syntax is detected.
     */
    String execute() throws InvalidCommandException;
}
