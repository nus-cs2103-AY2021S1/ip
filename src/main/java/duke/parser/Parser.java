package duke.parser;

import static duke.util.Keyword.KEYWORD_BYE;
import static duke.util.Keyword.KEYWORD_DEADLINE;
import static duke.util.Keyword.KEYWORD_DELETE;
import static duke.util.Keyword.KEYWORD_DONE;
import static duke.util.Keyword.KEYWORD_EVENT;
import static duke.util.Keyword.KEYWORD_FIND;
import static duke.util.Keyword.KEYWORD_HELP;
import static duke.util.Keyword.KEYWORD_LIST;
import static duke.util.Keyword.KEYWORD_MULTIPLE_SPACE;
import static duke.util.Keyword.KEYWORD_ONE_SPACE;
import static duke.util.Keyword.KEYWORD_REMIND;
import static duke.util.Keyword.KEYWORD_TODO;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.ReminderCommand;
import duke.commands.UnknownCommand;

/**
 * Class that simulates reading the user's input and making sense of it.
 */
public class Parser {

    /**
     * Make sense of the user's input and execute the correct command based on the user's input.
     */
    public static Command parse(String message) {
        assert message != null;
        String[] inputArr = getInputArray(message);
        inputArr[0] = inputArr[0].toLowerCase();
        switch(inputArr[0]) {
        case KEYWORD_BYE:
            return new ByeCommand(inputArr);
        case KEYWORD_LIST:
            return new ListCommand(inputArr);
        case KEYWORD_HELP:
            return new HelpCommand(inputArr);
        case KEYWORD_DONE:
            return new DoneCommand(inputArr);
        case KEYWORD_DELETE:
            return new DeleteCommand(inputArr);
        case KEYWORD_FIND:
            return new FindCommand(inputArr);
        case KEYWORD_REMIND:
            return new ReminderCommand(inputArr);
        case KEYWORD_EVENT:
            // Fallthrough
        case KEYWORD_DEADLINE:
            // Fallthrough
        case KEYWORD_TODO:
            return new AddCommand(inputArr);
        default:
            return new UnknownCommand(inputArr);
        }
    }

    /**
     * Format the user's input. Replacing multiple white spaces with single white space and splitting the string
     * based on the white spaces.
     *
     * @param message User's input.
     * @return A string array containing the user's input.
     */
    private static String[] getInputArray(String message) {
        return message.trim().replaceAll(KEYWORD_MULTIPLE_SPACE, KEYWORD_ONE_SPACE).split(KEYWORD_ONE_SPACE, 2);
    }
}
