package main.java.command;

import main.java.exception.InvalidArgumentException;

import java.util.List;

public class Commands {
    public static Command create(List<String> input) throws InvalidArgumentException {
        switch (input.get(0).toLowerCase()) {
            case "bye":
                return new ByeCommand(input);
            case "done":
                return new DoneCommand(input);
            case "clear":
                return new ClearCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "list":
                return new ListCommand(input);
            case "find":
                return new FindCommand(input);
            case "todo":
            case "deadline":
            case "event":
                return new TaskCommand(input);
            default:
                throw new InvalidArgumentException("Command not found");
        }
    }
}
