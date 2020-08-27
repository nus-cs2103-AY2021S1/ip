package main.java;

public class Parser {

    public static Command parse(String fullCommand) throws Exception {
        String[] splitCommand = fullCommand.split(" ", 2);
        String[] params;

        switch (splitCommand[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(splitCommand[1]);
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
