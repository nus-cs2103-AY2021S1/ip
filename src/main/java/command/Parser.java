package command;

import exceptions.CommandException;

import java.util.stream.Stream;

public class Parser {
    public static Command parseCommand(String input) throws CommandException {
        return Stream.of(Command.values())
                .filter(cmd -> cmd.isNoArgs()
                        ? input.equals(cmd.getCmdString())
                        : input.startsWith(cmd.getCmdString()))
                .findFirst()
                .orElseThrow(() -> new CommandException(input, "Unknown command"));
    }
}
