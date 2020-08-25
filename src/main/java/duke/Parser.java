package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parses the user's inputs and decides what to do depending on the input.
 */
public class Parser {

    /**
     * Returns a command object according to what the user input.
     * Checks for the integrity of the command and rejects it if it fails by throwing and error.
     *
     * @param input User's input.
     * @return command
     * @throws DukeException If the command fails the integrity checks.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandDetail = input.split(" ", 2);

        if (commandDetail.length == 0) {
            throw new DukeException("Be sure to follow the exact format of the commands!");
        }

        Commands command;
        try {
            command = Commands.valueOf(commandDetail[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry! I don't recognize that command!");
        }

        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            if (commandDetail.length < 2) {
                throw new DukeException("Please key in a task to find!");
            }
            return new FindCommand(commandDetail[1]);
        case DONE:
            if (commandDetail.length < 2) {
                throw new DukeException("Please key in a task to be marked as done!");
            }
            return new DoneCommand(commandDetail[1]);
        case DELETE:
            if (commandDetail.length < 2) {
                throw new DukeException("Please key in a task to delete!");
            }
            return new DeleteCommand(commandDetail[1]);
        case TODO:
        case DEADLINE:
        case EVENT:
            if (commandDetail.length < 2) {
                throw new DukeException("Please input a task to add!");
            }
            return new AddCommand(command, commandDetail[1]);
        default:
            throw new DukeException("Sorry! I don't recognize that command!");
        }
    }
}
