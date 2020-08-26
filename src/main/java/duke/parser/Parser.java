package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.UpdateCommand;
import duke.exceptions.DukeException;

/**
 * Represents the parser that parses the user's input.
 */
public class Parser {

    /**
     * Returns the respective Command object depending on the user input only if the input is a
     * valid command.
     *
     * @param input the command given by the user
     * @return Command object that corresponds to the user's input
     * @throws DukeException if the user inputs an unrecognisable command
     */
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

