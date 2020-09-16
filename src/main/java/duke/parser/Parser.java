package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Parser takes in and makes sense of the user input read by Ui.
 */
public class Parser {
    /**
     * Returns the relevant Command if user input is valid,
     * otherwise throws a DukeException.
     * @param fullCommand String of a line of user input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String prefix = arr[0];
        String suffix;
        if (arr.length > 1) {
            suffix = arr[1];
        } else {
            suffix = "";
        }
        switch(prefix) {
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
        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(suffix);
        default:
            throw new DukeException();
        }
    }

    private static Command prepareUpdate(String suffix) throws DukeException {
        if (!suffix.matches("\\d+")) {
            throw new DukeException("update should be followed by a single task number.");
        } else {
            return new UpdateCommand(Integer.parseInt(suffix));
        }
    }

    /**
     * Returns a TodoCommand if user input is valid.
     * @param suffix String that follows after "todo" command .
     */
    private static Command prepareTodo(String suffix) throws DukeException {
        if (suffix.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            return new TodoCommand(suffix);
        }
    }

    /**
     * Returns a DeadlineCommand if user input is valid.
     * @param suffix String that follows after "deadline" command.
     */
    private static Command prepareDeadline(String suffix) throws DukeException {
        try {
            String[] suffixArray = suffix.split("/by ");
            return new DeadlineCommand(suffixArray[0].strip(), suffixArray[1].strip());
        } catch (Exception e) {
            throw new DukeException("Ensure that deadlines have correct description.");
        }
    }

    /**
     * Returns a EventCommand if user input is valid.
     * @param suffix String that follows after "event" command.
     */
    private static Command prepareEvent(String suffix) throws DukeException {
        try {
            String[] suffixArray = suffix.split(Task.EVENT_FIELD_IDENTIFIER);
            return new EventCommand(suffixArray[0].strip(), suffixArray[1].strip());
        } catch (Exception e) {
            throw new DukeException("Ensure that events have correct description.");
        }
    }

    /**
     * Returns a DoneCommand if user input is valid.
     * @param suffix String that follows after "done" command.
     */
    private static Command prepareDone(String suffix) throws DukeException {
        if (!suffix.matches("\\d+")) {
            throw new DukeException("done should be followed by a single task number.");
        } else {
            return new DoneCommand(Integer.parseInt(suffix));
        }
    }

    /**
     * Returns a DeleteCommand if user input is valid.
     * @param suffix String that follows after "delete" command.
     */
    private static Command prepareDelete(String suffix) throws DukeException {
        if (!suffix.matches("\\d+")) {
            throw new DukeException("delete should be followed by a single task number.");
        } else {
            return new DeleteCommand(Integer.parseInt(suffix));
        }
    }

    /**
     * Returns a ListCommand if user input is valid.
     * @param suffix String that follows after "list" command.
     */
    private static Command prepareList(String suffix) throws DukeException {
        if (suffix.length() != 0) {
            throw new DukeException("Listing specific tasks not yet supported.");
        } else {
            return new ListCommand();
        }
    }

    /**
     * Returns a FindCommand if user input is valid.
     * @param suffix String that follows after "find" command.
     */
    private static Command prepareFind(String suffix) throws DukeException {
        if (suffix.length() == 0) {
            throw new DukeException("You cannot search for nothing.");
        } else {
            return new FindCommand(suffix);
        }
    }
}
