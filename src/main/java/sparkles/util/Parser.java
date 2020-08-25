package sparkles.util;

import sparkles.command.*;
import sparkles.command.addcommand.AddCommand;
import sparkles.command.addcommand.AddDeadlineCommand;
import sparkles.command.addcommand.AddEventCommand;
import sparkles.command.addcommand.AddTodoCommand;

public class Parser {

    public static Command parse(String command) {
        return parseCommand(command);
    }

    private static Command parseCommand(String command) {
        String lowerCase = command.toLowerCase();

        if (lowerCase.equals("list")) {
            return new ShowListCommand(command);
        } else if (lowerCase.startsWith("done")) {
            return new DoneCommand(command);
        } else if (lowerCase.equals("bye")) {
            return new ExitCommand(command);
        } else if (lowerCase.startsWith("delete")) {
            return new DeleteCommand(command);
        } else {
            return new AddCommand(command);
        }
    }

    public static Command parseAddCommand(String command) {
        String lowerCase = command.toLowerCase();

        if (lowerCase.startsWith("deadline")) {
            return new AddDeadlineCommand(command);
        } else if (lowerCase.startsWith("event")) {
            return new AddEventCommand(command);
        } else if ((lowerCase.startsWith("todo"))) {
            return new AddTodoCommand(command);
        } else {
            return new TaskUnclearCommand(command);
        }
    }
}
