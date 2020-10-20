package duke;

import exception.DukeErrorException;
import exception.UnknownCommandException;

/**
 * Represents parser class to parse user commands.
 */
public class Parser {

    /**
     * Verifies whether the command is valid or not.
     *
     * @param commandString user input.
     * @return a {@code Command} object representing user actions.
     * @throws UnknownCommandException - UnknownCommandException
     */
    public static Commands processInput(String commandString) throws UnknownCommandException {
        Commands cmd = Commands.valueOf(commandString);
        assert cmd != null : "Command should not be null";
        if (!cmd.equals(Commands.BYE)
                && !cmd.equals(Commands.EVENT)
                && !cmd.equals(Commands.DEADLINE)
                && !cmd.equals(Commands.DONE)
                && !cmd.equals(Commands.LIST)
                && !cmd.equals(Commands.TODO)
                && !cmd.equals(Commands.DELETE)
                && !cmd.equals((Commands.FIND))
                && !cmd.equals((Commands.UPDATE))) {
            throw new UnknownCommandException();
        }
        return cmd;
    }

    /**
     * Parses user commands.
     *
     * @param splitted user input.
     * @return a {@code Command} object representing user actions.
     * @throws DukeErrorException - DukeErrorException
     */
    public static Commands processCommand(String[] splitted) throws DukeErrorException {
        try {
            Commands command = processInput(splitted[0].toUpperCase());
            assert command != null : "Command should not be null";
            return command;
        } catch (IllegalArgumentException | UnknownCommandException ex) {
            System.out.println(ex);
        }

        throw new DukeErrorException("Invalid Input Format!");
    }
}
