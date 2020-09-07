package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Handles the parsing of user input to identify the command given by user.
 */
public class Parser {

    /**
     * Parses the string input provided by the user and returns the executable Command object.
     * @param fullCommand The string input provided by the user.
     * @return The Command identified from the user's input.
     * @throws DukeException Throws DukeException when the command cannot be identified, or is not valid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandArgs = fullCommand.split(" ");
        assert commandArgs.length > 0;
        if (commandArgs[0].equals(CommandType.BYE.getName())) {
            return new ExitCommand();
        } else if (commandArgs[0].equals(CommandType.LIST.getName())) {
            return createListCommand(commandArgs);
        } else if (commandArgs[0].equals(CommandType.DONE.getName())) {
            return createDoneCommand(commandArgs);
        } else if (commandArgs[0].equals(CommandType.DELETE.getName())) {
            return createDeleteCommand(commandArgs);
        } else if (commandArgs[0].equals(CommandType.TODO.getName())) {
            return createAddTodoCommand(commandArgs);
        } else if (commandArgs[0].equals(CommandType.DEADLINE.getName())) {
            return createAddDeadlineCommand(commandArgs);
        } else if (commandArgs[0].equals(CommandType.EVENT.getName())) {
            return createAddEventCommand(commandArgs);
        } else if (commandArgs[0].equals(CommandType.FIND.getName())) {
            return createFindCommand(commandArgs);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means...");
        }
    }

    private static ListCommand createListCommand(String[] commandArgs) {
        try {
            return new ListCommand(LocalDate.parse(commandArgs[1]));
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return new ListCommand();
        }
    }

    private static DoneCommand createDoneCommand(String[] commandArgs) throws DukeException {
        try {
            return new DoneCommand(Integer.parseInt(commandArgs[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be marked as done has to be provided.");
        }
    }

    private static DeleteCommand createDeleteCommand(String[] commandArgs) throws DukeException {
        try {
            return new DeleteCommand(Integer.parseInt(commandArgs[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be deleted has to be provided.");
        }
    }

    private static AddCommand createAddTodoCommand(String[] commandArgs) throws DukeException {
        String description = Parser.reassembleString(commandArgs, 1, commandArgs.length);
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddCommand(CommandType.TODO, description, null);
    }

    private static AddCommand createAddDeadlineCommand(String[] commandArgs) throws DukeException {
        int byIdx = Arrays.asList(commandArgs).indexOf("/by");
        if (byIdx < 0) {
            throw new DukeException("The deadline date has to be provided to the deadline task.");
        }

        String description = Parser.reassembleString(commandArgs, 1, byIdx);
        String by = Parser.reassembleString(commandArgs, byIdx + 1, commandArgs.length);
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (by.equals("")) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }

        return new AddCommand(CommandType.DEADLINE, description, by);
    }

    private static AddCommand createAddEventCommand(String[] commandArgs) throws DukeException {
        int atIdx = Arrays.asList(commandArgs).indexOf("/at");
        if (atIdx < 0) {
            throw new DukeException("The event date has to be provided to the event task.");
        }
        String description = Parser.reassembleString(commandArgs, 1, atIdx);
        String at = Parser.reassembleString(commandArgs, atIdx + 1, commandArgs.length);
        if (description.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (at.equals("")) {
            throw new DukeException("The date of an event cannot be empty.");
        }
        return new AddCommand(CommandType.EVENT, description, at);
    }

    private static FindCommand createFindCommand(String[] commandArgs) throws DukeException {
        String keyword = Parser.reassembleString(commandArgs, 1, commandArgs.length);
        if (keyword.length() == 0) {
            throw new DukeException("The keyword has to be provided for the find command");
        }
        return new FindCommand(keyword);
    }

    private static String reassembleString(String[] arr, int from, int to) {
        assert from >= 0 && to <= arr.length;
        return String.join(" ", Arrays.copyOfRange(arr, from, to));
    }
}
