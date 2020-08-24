package duke;

import duke.command.*;

/**
 * A Parser to take in user input and determines the Command given to Duke.
 */
public class Parser {

    /**
     * Parse user input and returns an enum of the command.
     *
     * @param fullCommand is the user input from the terminal.
     * @return an enum representing the command from the user.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitString = fullCommand.split(" ");
        Commands c;
        try {
            c = Commands.valueOf(splitString[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            c = Commands.UNKNOWN;
        }

        switch (c) { // Determine output from user input
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(fullCommand);
        case TODO:
        case EVENT:
        case DEADLINE:
            return new AddCommand(fullCommand, c);
        case DONE:
            return new DoneCommand(fullCommand);
        case DELETE:
            return new DeleteCommand(fullCommand);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

    }
}
