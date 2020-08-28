package duke;

import exception.DukeErrorException;
import exception.UnknownCommandException;

/**
 * Represents parser class to parse user commands
 */
public class Parser {

    /**
     * Method to verify the command is valid or not
     *
     * @param commandString user input
     * @return a {@code Command} object representing user actions
     */
    public static Commands processInput(String commandString) throws UnknownCommandException {
        Commands cmd = Commands.valueOf(commandString);
        if (!cmd.equals(Commands.BYE) &&
                !cmd.equals(Commands.EVENT) &&
                !cmd.equals(Commands.DEADLINE) &&
                !cmd.equals(Commands.DONE) &&
                !cmd.equals(Commands.LIST) &&
                !cmd.equals(Commands.TODO) &&
                !cmd.equals(Commands.DELETE)) {
            throw new UnknownCommandException();
        }
        return cmd;
    }

    /**
     * Main method to call to parse user commands
     *
     * @param splitted user input
     * @return a {@code Command} object representing user actions
     */
    public static Commands processCommand(String[] splitted) throws DukeErrorException {
        try {
            Commands command = processInput(splitted[0].toUpperCase());

            return command;
        } catch (IllegalArgumentException | UnknownCommandException ex) {
            System.out.println(ex);
        }

        throw new DukeErrorException("Invalid Input Format!");
    }
}
