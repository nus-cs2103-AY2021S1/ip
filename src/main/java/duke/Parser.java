package duke;

import exception.DukeErrorException;
import exception.UnknownCommandException;

public class Parser {

    public static Commands processInput(String commandString) throws UnknownCommandException {
        Commands cmd = Commands.valueOf(commandString);
        if (!cmd.equals(Commands.BYE) &&
                !cmd.equals(Commands.EVENT) &&
                !cmd.equals(Commands.DEADLINE) &&
                !cmd.equals(Commands.DONE) &&
                !cmd.equals(Commands.LIST) &&
                !cmd.equals(Commands.TODO) &&
                !cmd.equals(Commands.DELETE) &&
                !cmd.equals((Commands.FIND))) {
            throw new UnknownCommandException();
        }
        return cmd;
    }

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
