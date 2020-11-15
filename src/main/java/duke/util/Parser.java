package duke.util;

import java.util.Arrays;
import java.util.Date;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.exception.ForbiddenCharacterException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidFindException;
import duke.exception.InvalidToDoException;

/**
 * Parses an input into a command.
 */
public class Parser {
    /**
     * Parses a String input into a command.
     *
     * @param input The input to parse.
     * @return Command associated with the input.
     * @throws DukeException If the input does not match any command.
     */
    public static Command parse(String input) throws DukeException {
        if (input.contains("|")) {
            throw new ForbiddenCharacterException();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.matches("delete \\d+")) {
            return parseDelete(input);
        } else if (input.matches("done \\d+")) {
            return parseDone(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            return parseTodo(input);
        } else if (input.startsWith("find")) {
            return parseFind(input);
        } else if (input.equals("sort")) {
            return new SortCommand();
        } else {
            throw new DukeException();
        }
    }

    private static Command parseDeadline(String input) throws InvalidDeadlineException {
        try {
            String[] inputArray = input.substring(input.indexOf(' ') + 1).split(" /by ");
            String description = inputArray[0];
            Date by = DateFormatter.parseDate(inputArray[1]);
            return new DeadlineCommand(description, by);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            throw new InvalidDeadlineException();
        }
    }

    private static Command parseDelete(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        return new DeleteCommand(index);
    }

    private static Command parseDone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        return new DoneCommand(index);
    }

    private static Command parseEvent(String input) throws InvalidEventException {
        try {
            String[] inputArray = input.substring(input.indexOf(' ') + 1).split(" /at ");
            String description = inputArray[0];
            Date at = DateFormatter.parseDate(inputArray[1]);
            return new EventCommand(description, at);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            throw new InvalidEventException();
        }
    }

    private static Command parseTodo(String input) throws InvalidToDoException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new InvalidToDoException();
        }
        String[] descriptionArray = Arrays.copyOfRange(split, 1, split.length);
        String description = String.join(" ", descriptionArray);
        return new ToDoCommand(description);
    }

    private static Command parseFind(String input) throws InvalidFindException {
        String[] split = input.split("find ");
        if (split.length == 0 || split.length == 1) {
            throw new InvalidFindException();
        }
        String restOfInput = split[1];
        String[] keywords = restOfInput.split("\\s*~\\s*"); // splits and trims

        return new FindCommand(keywords);
    }
}
