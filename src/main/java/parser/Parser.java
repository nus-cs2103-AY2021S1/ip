package parser;

import command.*;
import constant.DukeConstants;
import enums.TaskEnum;


public class Parser {

    public Command parse(String input) {
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

    private boolean isListCommand(String input) {
        return input.equals(DukeConstants.LIST_COMMAND);
    }

    private boolean isDoneCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.DONE_COMMAND);
    }

    private boolean isDeleteCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.DELETE_COMMAND);
    }

    private boolean isTodoCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.TODO_COMMAND);
    }

    private boolean isDeadlineCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.DEADLINE_COMMAND);
    }
    private boolean isEventCommand(String input) {
        return input.split("\\s+")[0].equals(DukeConstants.EVENT_COMMAND);
    }

    public boolean isExit(String input) {
        return input.equals(DukeConstants.EXIT_INPUT);
    }
}
