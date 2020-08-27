package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;
import duke.command.*;


public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String prefix = arr[0];
        String suffix;
        if (arr.length > 1) {
            suffix = arr[1];
        } else {
            suffix = "";
        }

        switch (prefix) {
            case TodoCommand.COMMAND_WORD:
                return prepareTodo(suffix);
            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(suffix);
            case EventCommand.COMMAND_WORD:
                return prepareEvent(suffix);
            case DoneCommand.COMMAND_WORD:
                return prepareDone(suffix);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(suffix);
            case ListCommand.COMMAND_WORD:
                return prepareList(suffix);
            case FindCommand.COMMAND_WORD:
                return prepareFind(suffix);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            default:
                throw new DukeException();
        }
    }

    private static Command prepareTodo(String suffix) throws DukeException {
        if (suffix.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            return new TodoCommand(suffix);
        }
    }

    private static Command prepareDeadline(String suffix) throws DukeException {
        try {
            String[] suffixArray = suffix.split("/by ");
            return new DeadlineCommand(suffixArray[0].strip(), suffixArray[1].strip());
        } catch (Exception e) {
            throw new DukeException("Ensure that deadlines have correct description.");
        }
    }

    private static Command prepareEvent(String suffix) throws DukeException {
        try {
            String[] suffixArray = suffix.split("/at ");
            return new EventCommand(suffixArray[0].strip(), suffixArray[1].strip());
        } catch (Exception e) {
            throw new DukeException("Ensure that events have correct description.");
        }
    }

    private static Command prepareDone(String suffix) throws DukeException {
        if (!suffix.matches("\\d+")) {
            throw new DukeException("done should be followed by a single task number.");
        } else {
            return new DoneCommand(Integer.parseInt(suffix));
        }
    }

    private static Command prepareDelete(String suffix) throws DukeException {
        if (!suffix.matches("\\d+")) {
            throw new DukeException("delete should be followed by a single task number.");
        } else {
            return new DeleteCommand(Integer.parseInt(suffix));
        }
    }

    private static Command prepareList(String suffix) throws DukeException {
        if (suffix.length() != 0) {
            throw new DukeException();
        } else {
            return new ListCommand();
        }
    }

    private static Command prepareFind(String suffix) throws DukeException {
        if (suffix.length() == 0) {
            throw new DukeException("You cannot search for nothing.");
        } else {
            return new FindCommand(suffix);
        }
    }
}
