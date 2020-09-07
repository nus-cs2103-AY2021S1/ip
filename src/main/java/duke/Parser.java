package duke;

import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.CreateDeadlineCommand;
import duke.command.CreateEventCommand;
import duke.command.CreateTodoCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidInputCommand;
import duke.command.ListTasksCommand;

import java.time.LocalDate;

/**
 * Encapsulates a parser to read user inputs in duke and determine the subsequent actions
 */
class Parser {

    /**
     * Symbol used to separate values in a date.
     * "-" is used by LocalDate.toString()
     */
    private final static String DATE_SEPARATOR = "-";

    /**
     * Parses the given input from the user.
     *
     * @param input Input from user
     * @return Command corresponding to the input
     */
    static Command parse(String input) {
        assert input != null;

        // Parses initial input into prefix and body
        String[] parsedCommand = input.split(" ", 2);
        String prefix = parsedCommand[0];
        String body = null;
        if (parsedCommand.length == 2) {
            body = parsedCommand[1];
        }

        // Command determined by prefix
        switch(prefix) {
        case("bye"):
            return parseExit(body);
        case("deadline"):
            return parseDeadline(body);
        case("delete"):
            return parseDelete(body);
        case("done"):
            return parseDone(body);
        case("event"):
            return parseEvent(body);
        case("find"):
            return parseFind(body);
        case("list"):
            return parseList(body);
        case("todo"):
            return parseTodo(body);
        }

        return parseInvalidInput();
    }

    /**
     * Converts a string representation of a date to a LocalDate.
     *
     * @param input String representation of a date
     * @return LocalDate of the date
     * @throws NumberFormatException Exception thrown if the string cannot be parsed into integers
     */
    static LocalDate genDate(String input) throws NumberFormatException {
        assert input != null;

        String[] strings = input.split(Parser.DATE_SEPARATOR, 3);
        int[] ints = new int[3];
        for(int i = 0; i < 3; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return LocalDate.of(ints[0], ints[1], ints[2]);
    }

    /**
     * Parses the given input into a CreateDeadlineCommand.
     *
     * @param body
     * @return CreateDeadlineCommand. If the input is invalid, it is parsed as invalid input.
     */
    private static Command parseDeadline(String body) {
        try {
            String[] parsedDeadline = body.split(" /by ", 2);
            LocalDate date = Parser.genDate(parsedDeadline[1]);
            return new CreateDeadlineCommand(parsedDeadline[0], false, date);
        } catch (NullPointerException | NumberFormatException e) {
            return parseInvalidInput();
        }
    }

    /**
     * Parses the given input into a DeleteTaskCommand.
     *
     * @param body
     * @return DeleteTaskCommand. If the input is invalid, it is parsed as invalid input.
     */
    private static Command parseDelete(String body) {
        try {
            int taskIndex = Integer.parseInt(body);
            return new DeleteTaskCommand(taskIndex - 1);
        } catch (NumberFormatException e) {
            return parseInvalidInput();
        }
    }

    /**
     * Parses the given input into a CompleteTaskCommand.
     *
     * @param body
     * @return CompleteTaskCommand. If the input is invalid, it is parsed as invalid input.
     */
    private static Command parseDone(String body) {
        try {
            int taskIndex = Integer.parseInt(body);
            return new CompleteTaskCommand(taskIndex - 1);
        } catch (NumberFormatException e) {
            return parseInvalidInput();
        }
    }

    /**
     * Parses the given input into a CreateEventCommand.
     *
     * @param body
     * @return CreateEventCommand. If the input is invalid, it is parsed as invalid input.
     */
    private static Command parseEvent(String body) {
        try {
            String[] parsedEvent = body.split(" /at ", 2);
            LocalDate date = Parser.genDate(parsedEvent[1]);
            return new CreateEventCommand(parsedEvent[0], false, date);
        } catch (NullPointerException | NumberFormatException e) {
            return parseInvalidInput();
        }
    }

    /**
     * Parses the given input into an ExitCommand.
     *
     * @param body
     * @return ExitCommand
     */
    private static ExitCommand parseExit(String body) {
        return new ExitCommand();
    }

    /**
     * Parses the given input into a FindCommand.
     *
     * @param body
     * @return FindCommand
     */
    private static FindCommand parseFind(String body) {
        return new FindCommand(body);
    }

    /**
     * Parses the given input into a ListTasksCommand.
     *
     * @param body
     * @return ListTasksCommand
     */
    private static ListTasksCommand parseList(String body) {
        return new ListTasksCommand();
    }

    /**
     * Parses the invalid input.
     *
     * @return InvalidInputCommand
     */
    private static InvalidInputCommand parseInvalidInput() {
        return new InvalidInputCommand();
    }

    /**
     * Parses the given input into a CreateTodoCommand.
     *
     * @param body
     * @return CreateTodoCommand
     */
    private static CreateTodoCommand parseTodo(String body) {
        return new CreateTodoCommand(body, false);
    }
}
