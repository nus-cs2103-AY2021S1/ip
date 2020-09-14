package duke;

import duke.commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Implements a Parser to parse user commands or dates
 */
public class Parser {

    /**
     * Parses the user command
     * @param command given user input in string format
     */
    public Command parseCommand(String command) throws DukeException {
        String[] simpleSplitArgs = command.split(" ", 2);
        String[] tagSplitArgs = new String[]{""};
        if (simpleSplitArgs.length > 1) {
            tagSplitArgs = simpleSplitArgs[1].split(" #"); // tagSplitArgs[1]~ all tags if tagSplitArgs[0] is not ""
        }

        String commandType = simpleSplitArgs[0];

        if (commandType.equals(CommandType.BYE.getName())) {
            return new ExitCommand();
        } else if (commandType.equals(CommandType.LIST.getName())) {
            return new ListCommand();
        } else if (commandType.equals(CommandType.DONE.getName())) {
            return new DoneCommand(Integer.parseInt(simpleSplitArgs[1]));
        } else if (commandType.equals(CommandType.DELETE.getName())) {
            if (simpleSplitArgs.length > 1) {
                return new DeleteCommand(Integer.parseInt(simpleSplitArgs[1]));
            } else {
                throw new DukeException("you need index for deleting (hint: delete <index>)!");
            }
        } else if (commandType.equals(CommandType.FIND.getName())) {
            if (simpleSplitArgs.length > 1) {
                return new FindCommand(simpleSplitArgs[1]);
            } else {
                throw new DukeException("you need keyword for filtering (hint: find <index>)!");
            }
            // TOOD: Add function to find by tags
        } else if (commandType.equals(CommandType.TODO.getName())) {
            if (simpleSplitArgs.length > 1) {
                return new AddCommand(CommandType.TODO, simpleSplitArgs[1], tagSplitArgs, null);
            } else {
                throw new DukeException("you need description for todo (hint: todo <some description>)!");
            }
        } else if (commandType.equals(CommandType.DEADLINE.getName())) {
            String[] allSplitArgs = simpleSplitArgs[1].split(" /by ");
            if (allSplitArgs.length > 1) {
                return new AddCommand(CommandType.DEADLINE, allSplitArgs[0], tagSplitArgs, parseDateAndTime(allSplitArgs[1]));
            } else {
                throw new DukeException("you need date and time for deadline (hint: deadline something /by YYYY-MM-DD)!");
            }
        } else if (commandType.equals(CommandType.EVENT.getName())) {
            String[] allSplitArgs = simpleSplitArgs[1].split(" /at ");
            if (allSplitArgs.length > 1) {
                return new AddCommand(CommandType.EVENT, allSplitArgs[0], tagSplitArgs, parseDateAndTime(allSplitArgs[1]));
            } else {
                throw new DukeException("you need date and time for event (hint: event something /at YYYY-MM-DD)!");
            }
        } else {
            throw new DukeException("Uncle Roger dunno any command like that!");
        }
    }

    /**
     * Parses the date and time String into a LocalDate object
     * @param dateAndTime string containing date and time info
     * @return date LocalDate object corresponding to input String
     */
    private LocalDate parseDateAndTime (String dateAndTime) throws DukeException {
        try {
            return LocalDate.parse(dateAndTime, DateTimeFormatter.ISO_DATE);
        } catch(DateTimeParseException e) {
            throw new DukeException("wrong date format lah! Use YYYY-MM-DD.");
        }
    }
}
