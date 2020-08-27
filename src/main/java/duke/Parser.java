package duke;

import exception.UnknownCommandException;

public class Parser {

    public void processInput(Commands cmd) throws UnknownCommandException {
        if (!cmd.equals(Commands.BYE) &&
                !cmd.equals(Commands.EVENT) &&
                !cmd.equals(Commands.DEADLINE) &&
                !cmd.equals(Commands.DONE) &&
                !cmd.equals(Commands.LIST) &&
                !cmd.equals(Commands.TODO) &&
                !cmd.equals(Commands.DELETE)) {
            throw new UnknownCommandException();
        }
    }

    public Commands processCommand(String[] splitted, Parser parser) {
        try {
            Commands command = Commands.valueOf(splitted[0].toUpperCase());
            parser.processInput(command);

            return command;
        } catch (IllegalArgumentException | UnknownCommandException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
