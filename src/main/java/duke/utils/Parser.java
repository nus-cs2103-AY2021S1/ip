package duke.utils;

import duke.Duke;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;


/**
 * Handles all the commands from the user.
 */
public class Parser {

    /**
     * Converts the users input from string to Command type.
     *
     * @param input raw unprocessed string the user entered.
     * @return Command which the user wanted.
     * @throws DukeException if the command format is wrong.
     */
    public static Command parse(String input) throws DukeException {
        String trimmed = input.trim();

        if (trimmed.equals("")) {
            throw new DukeException("Please type a duke command");
        }

        String[] keywords = findKeywords(input.trim());

        if (keywords.length == 1) {
            throw new DukeException("Please enter a valid command");
        }

        // todo calls command class to get a enum of command type.
        CommandType commandType = Command.containsKeyword(keywords[0]);

        // todo create test case for parse methods.

        // todo switch through the enums.

        switch (commandType) {
        case EXIT:
            return new ExitCommand();
        case CLEAR:
            return new ClearCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(Integer.parseInt(input.substring(5)) - 1);
        case DELETE:
            return new DeleteCommand(Integer.parseInt(input.substring(7)) - 1);
        case DEADLINE:
            try {
                int by = input.indexOf(" /by");
                return new DeadlineCommand(input.substring(9, by), input.substring(by + 5));
            } catch (Exception e) {
                throw new DukeException("Deadline format isn't correct");
            }
        case EVENT:
            try {
                int at = input.indexOf(" /at");
                return new EventCommand(input.substring(6, at), input.substring(at + 5));
            } catch (Exception e) {
                throw new DukeException("Event format isn't correct");
            }
        case TODO:
            return new TodoCommand(input.substring(5));
        case FIND:
            try {
                return new FindCommand(input.substring(5));
            } catch (Exception e) {
                throw new DukeException("Find format isn't correct");
            }
        case UNKNOWN:
        default:
            throw new DukeException("I don't know what that means :( ");
        }
    }


    /**
     * Converts string into array of keywords.
     * Split the strings by the spacing.
     *
     * @param stringToConvert the string to be converted.
     * @return array with commands.
     */
    private static String[] findKeywords(String stringToConvert) {
        return stringToConvert.split(" ");
    }


}
