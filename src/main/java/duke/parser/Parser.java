package duke.parser;

import duke.command.*;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.WrongSyntaxException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {

    private final Set<String> commands = new HashSet<>(Arrays.asList("bye", "list", "done", "delete", "deadline",
                "event", "todo"));
    private final Set<String> singleArgCommands = new HashSet<>(Arrays.asList("bye", "list"));

    public Command parse(String commandStr) throws DukeException {
        String[] parts = split(commandStr);
        String commandName = parts[0].trim();
        String commandArgs = parts[1].trim();
        switch(commandName) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            int doneNumber = getTaskNumber(commandArgs);
            return new DoneCommand(doneNumber);
        case "delete":
            int deleteNumber = getTaskNumber(commandArgs);
            return new DeleteCommand(deleteNumber);
        case "deadline":
            String[] deadlineParts = splitTime("deadline", commandArgs);
            return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
        case "event":
            String[] eventParts = splitTime("event", commandArgs);
            return new EventCommand(eventParts[0].trim(), eventParts[1].trim());
        case "todo":
            return new TodoCommand(commandArgs);
        default:
            throw new UnknownCommandException(commandName);
        }
    }

    private int getTaskNumber(String commandArgs) throws WrongSyntaxException {
        try {
            int result = Integer.parseInt(commandArgs.trim());
            return result;
        } catch (NumberFormatException e) {
            throw new WrongSyntaxException();
        }
    }

    private String[] splitTime (String type, String commandArgs) throws WrongSyntaxException {
        String splitBy = type.equals("deadline") ? "by" : "at";
        String[] parts = commandArgs.split(splitBy, 2);
        if (parts.length != 2) {
            throw new WrongSyntaxException();
        } else {
            return parts;
        }
    }

    private String[] split(String commandStr) throws UnknownCommandException, WrongSyntaxException {
        if (commandStr.isBlank()) {
            throw new WrongSyntaxException();
        }
        String[] parts = commandStr.trim().split(" ", 2);
        String commandName = parts[0].trim();
        boolean isSingleArgCommand = singleArgCommands.contains(commandName);
        if (parts.length < 2 && !isSingleArgCommand) {
            throw new WrongSyntaxException();
        }
        if (!commands.contains(commandName)) {
            throw new UnknownCommandException(commandName);
        }
        if (isSingleArgCommand) {
            return new String[]{ commandName, "" };
        } else {
            String commandArgs = parts[1].trim();
            return new String[]{ commandName, commandArgs };
        }
    }
}
