package main.java;

import java.util.Comparator;

/**
 * The parser deals with making sense of the user command
 *
 * @author Lio
 */
public class Parser {
    /**
     * Parses the user input into a command that the chatbot can understand
     *
     * @param fullCommand The user's command
     * @return A Command Object
     * @throws Exception If the command is invalid
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] splitCommand = fullCommand.split(" ", 2);
        String[] params;

        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            if (splitCommand.length == 1) {
                return new ListCommand();
            }
            switch (splitCommand[1]) {
            case "name":
                return new ListCommand(Comparator.comparing(a -> a.name));
            case "date":
                return new ListCommand((a, b) -> {
                    if (a.date == null && b.date == null) {
                        return 0;
                    } else if (a.date == null) {
                        return 1;
                    } else if (b.date == null) {
                        return -1;
                    } else {
                        return a.date.compareTo(b.date);
                    }
                });
            default:
                return new ListCommand();
            }
        case "done":
            return new DoneCommand(Integer.parseInt(splitCommand[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitCommand[1]));
        case "todo":
            return new AddCommand("T | false | " + splitCommand[1]);
        case "deadline":
            params = splitCommand[1].split(" /by ");
            return new AddCommand("D | false | " + params[0] + " | " + params[1]);
        case "event":
            params = splitCommand[1].split(" /at ");
            return new AddCommand("E | false | " + params[0] + " | " + params[1]);
        default:
            throw new Exception("I'm sorry, but I don't know what that means :(");
        }
    }
}
