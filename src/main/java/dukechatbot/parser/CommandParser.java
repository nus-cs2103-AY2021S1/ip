package dukechatbot.parser;

import java.util.Objects;

import dukechatbot.command.AddCommand;
import dukechatbot.command.Command;
import dukechatbot.command.DeleteCommand;
import dukechatbot.command.DoneCommand;
import dukechatbot.command.ExitCommand;
import dukechatbot.command.FindCommand;
import dukechatbot.command.ListCommand;
import dukechatbot.constant.DukeConstants;
import dukechatbot.enums.TaskEnum;

/**
 * Parses the input to form the appropriate command.
 * If input is invalid, the command is null.
 */
public class CommandParser {

    /**
     * Parses the input to form the command object.
     * 
     * @param input
     * @return Command object related to the input to Duke.
     * @throws IndexOutOfBoundsException
     */
    public static Command parse(String input) throws IndexOutOfBoundsException {
        if (isListCommand(input)) {
            return new ListCommand(input);
        } else if (isDoneCommand(input)) {
            return new DoneCommand(input);
        } else if (isDeleteCommand(input)) {
            return new DeleteCommand(input);
        } else if (isTodoCommand(input)) {
            return new AddCommand(input, TaskEnum.TODO);
        } else if (isDeadlineCommand(input)) {
            return new AddCommand(input, TaskEnum.DEADLINE);
        } else if (isEventCommand(input)) {
            return new AddCommand(input, TaskEnum.EVENT);
        } else if (isFindCommand(input)) {
            return new FindCommand(input);
        } else if (isExitCommand(input)) {
            return new ExitCommand(input);
        } else {
            return null;
        }
    }

    private static boolean isListCommand(String input) {
        return input.equals(DukeConstants.LIST_COMMAND);
    }

    private static boolean isDoneCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.DONE_COMMAND);
    }

    private static boolean isDeleteCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.DELETE_COMMAND);
    }

    private static boolean isTodoCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.TODO_COMMAND);
    }

    private static boolean isDeadlineCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.DEADLINE_COMMAND);
    }
    
    private static boolean isEventCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.EVENT_COMMAND);
    }

    private static boolean isFindCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.FIND_COMMAND);
    }
    
    private static boolean isExitCommand(String input) {
        return input.equals(DukeConstants.EXIT_INPUT);
    }

    /**
     * Returns the title of the input.
     * @param input
     * @return Title of the input
     * @throws IndexOutOfBoundsException
     */
    public static String getTitle(String input) throws IndexOutOfBoundsException {
        assert(!Objects.isNull(input));
        try {
            return input.split("\\s+", 2)[1];
        } catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException(
                    "\u2639 OOPS!!! The description of a task cannot be empty.");
        }
    }
}
