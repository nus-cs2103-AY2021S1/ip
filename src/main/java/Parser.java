import exceptions.DukeException;
import exceptions.UnknownCommandException;
import exceptions.WrongSyntaxException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {

    private final Set<String> commands = new HashSet<>(Arrays.asList("bye", "list", "done", "delete", "deadline",
                "event", "todo"));

    public Command parse(String commandStr) throws DukeException {
        String[] parts = split(commandStr);
        String commandName = parts[0];
        String commandArgs = parts[1];
        switch(commandName) {
        case "bye":
        case "list":
        case "done":
        case "delete":
        case "deadline":
        case "event":
        case "todo":
        default:
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
        String[] parts = commandStr.split(" ", 2);
        if (parts.length < 2) {
            throw new WrongSyntaxException();
        }
        String commandName = parts[0];
        if (!commands.contains(commandName)) {
            throw new UnknownCommandException(commandName);
        } else {
            return parts;
        }
    }
}
