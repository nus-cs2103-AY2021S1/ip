package command;

import exceptions.CommandException;

import java.util.stream.Stream;

public class Parser {

    /**
     * Parses an input command to determine what command it is.
     * @param input the input command as a String
     * @return the {@code Command} that this string corresponds to
     * @throws CommandException if the input does not match any known commands
     */
    public static Command parseCommand(String input) throws CommandException {
        return Stream.of(Command.values())
                .filter(cmd -> cmd.isNoArgs()
                        ? input.equals(cmd.getCmdString())
                        : input.startsWith(cmd.getCmdString()))
                .findFirst()
                .orElseThrow(() -> new CommandException(input, "Unknown command"));
    }
}
