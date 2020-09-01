package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ListCommand;
import constant.DukeConstants;
import enums.TaskEnum;


public class CommandParser {

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

    public static boolean isExit(String input) {
        return input.equals(DukeConstants.EXIT_INPUT);
    }

    public static String getTitle(String input) throws IndexOutOfBoundsException {
        try {
            return input.split("\\s+", 2)[1];
        } catch(IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
    }
}
