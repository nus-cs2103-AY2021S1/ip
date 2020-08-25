package duke.Parser;

import duke.Commands.*;
import duke.exceptions.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        if (input.startsWith("todo")) {
            return prepareAddTodo(input);
        } else if (input.startsWith("deadline")) {
            return prepareAddDeadline(input);
        } else if (input.startsWith("event")) {
            return prepareAddEvent(input);
        } else if ("list".equals(input)) {
            return new ListCommand(input);
        } else if (input.startsWith("done")) {
            return prepareUpdateTask(input);
        } else if (input.startsWith("delete")) {
            return prepareDeleteTask(input);
        } else if ("bye".equals(input)) {
            return new ExitCommand(input);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command prepareAddTodo(String command) {
        String description = command.substring(4).trim();
        return new AddCommand(AddCommand.Type.TODO, description);
    }

    private static Command prepareAddDeadline(String command) {
        String description = command.substring(8).trim();
        return new AddCommand(AddCommand.Type.DEADLINE, description);
    }

    private static Command prepareAddEvent(String command) {
        String description = command.substring(5).trim();
        return new AddCommand(AddCommand.Type.EVENT, description);
    }

    private static Command prepareUpdateTask(String command) {
                String todoIndex = command.substring(4).trim();
                return new UpdateCommand(command, todoIndex);
    }

    private static Command prepareDeleteTask(String command) {
        String todoIndex = command.substring(6).trim();
        return new DeleteCommand(command, todoIndex);
    }
}

