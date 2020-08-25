package duke;

import duke.command.*;
import duke.exception.*;

public class Parser {

    private static final String LIST_COMMAND = "list";
    private static final String END_COMMAND = "bye";
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_BY_DATE_COMMAND = "find_by_date";
    private static final String VIEW_FUNCTION_COMMAND = "/commands";

    public static Command parse(String userInput) throws InvalidFunctionException {
        String message = userInput.trim();
        String[] parsedCommand = message.split(" ");
        String function = parsedCommand[0];
        if (message.equals(Parser.END_COMMAND)) {
            return new ExitCommand(parsedCommand);
        } else if (message.equals(Parser.VIEW_FUNCTION_COMMAND)) {
            return new ViewFunctionsCommand(parsedCommand);
        } else if (message.isEmpty()) {
            String err = "No input was entered! Please enter something!";
            throw new InvalidFunctionException(err);
        } else if (message.equals(Parser.LIST_COMMAND)) {
            return new ListCommand(parsedCommand);
        } else if (function.equals(Parser.DONE_COMMAND)) {
            return new CompleteTaskCommand(parsedCommand);
        } else if (function.equals(Parser.ADD_TODO_COMMAND)){
            parsedCommand = message.split("todo");
            return new AddTodoCommand(parsedCommand);
        } else if (function.equals(Parser.ADD_DEADLINE_COMMAND)) {
            parsedCommand = message.split("deadline");
            return new AddDeadlineCommand(parsedCommand);
        } else if (function.equals(Parser.ADD_EVENT_COMMAND)) {
            parsedCommand = message.split("event");
            return new AddEventCommand(parsedCommand);
        } else if (function.equals(Parser.DELETE_COMMAND)) {
            return new DeleteTaskCommand(parsedCommand);
        } else if (function.equals(Parser.FIND_BY_DATE_COMMAND)) {
            return new FindByDateCommand(parsedCommand);
        } else {
            String err = "Invalid Function! Input '/commands' for a list of all my commands.";
            throw new InvalidFunctionException(err);
        }
    }

}
