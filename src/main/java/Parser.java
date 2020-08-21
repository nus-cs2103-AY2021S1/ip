package main.java;

public class Parser {

    public static Command parse (String fullCommand) {
        String[] parsed = fullCommand.split(" ",2);
        if (parsed[0].equals("list")) {
            return new PrintListCommand(parsed);
        } else if (parsed[0].equals("done")) {
            return new DoneCommand(parsed);
        } else if (parsed[0].equals("delete")) {
            return new DeleteCommand(parsed);
        } else if (parsed[0].equals("event")) {
            return new AddEventCommand(parsed);
        } else if (parsed[0].equals("deadline")) {
            return new AddDeadlineCommand(parsed);
        } else if (parsed[0].equals("todo")) {
            return new AddTodoCommand(parsed);
        } else if (parsed[0].equals("bye")) {
            return new ExitCommand(parsed);
        } else {
            return new UnknownCommand(parsed);
        }

    }


}
