package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

/**
 * Utility class that parses user's input and checks for command correctness before
 * converting the input into executable commands.
 */
public class Parser {

    /**
     * Parses user's input.
     * @param inputCommand Input command from the user.
     * @return An executable command.
     * @throws DukeException If command provided is invalid.
     */
    public static Command parse(String inputCommand) throws DukeException {
        assert inputCommand != null;

        String[] inputArr = inputCommand.split(" ", 2);
        String commandArg = inputArr.length == 2 ? inputArr[1] : null;

        CommandType op;
        try {
            op = CommandType.valueOf(inputArr[0].toUpperCase()); // type of operation
        } catch (IllegalArgumentException e) {
            op = CommandType.INVALID;
        }

        switch (op) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseAddCommand(op, commandArg);
        case DONE:
            return parseDoneCommand(commandArg);
        case DELETE:
            return parseDeleteCommand(commandArg);
        case FIND:
            return parseFindCommand(commandArg);
        case INVALID:
        default:
            return new InvalidCommand();
        }
    }

    private static AddCommand parseAddCommand(CommandType taskType, String taskDetail) throws DukeException {
        if (taskDetail == null) {
            throw new DukeException("You have to tell me what's your task!");
        }

        switch(taskType) {
        case TODO:
            String[] todoContents = {taskDetail};
            return new AddCommand(taskType, todoContents);
        case DEADLINE:
            String[] deadlineContents = taskDetail.split(" /by ");
            if (deadlineContents.length < 2) {
                throw new DukeException("You need to tell me when this task is due!");
            }
            return new AddCommand(taskType, deadlineContents);
        case EVENT:
            String[] eventContents = taskDetail.split( " /at ");
            if (eventContents.length < 2) {
                throw new DukeException("You need to tell me when this event is happening!");
            }
            return new AddCommand(taskType, eventContents);
        default:
            throw new DukeException("Unknown task type.");
        }
    }

    private static DoneCommand parseDoneCommand(String commandArg) throws DukeException {
        int taskIdx;
        try {
            taskIdx = Integer.parseInt(commandArg.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry I'm not sure what task do you want me to mark as done!");
        }
        return new DoneCommand(taskIdx);
    }

    private static DeleteCommand parseDeleteCommand(String commandArg) throws DukeException {
        int taskIdx;
        try {
            taskIdx = Integer.parseInt(commandArg.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry I'm not sure what task do you want me to delete!");
        }
        return new DeleteCommand(taskIdx);
    }

    private static FindCommand parseFindCommand(String commandArg) throws DukeException {
        if (commandArg == null) {
            throw new DukeException("You have to tell me what to search for!");
        }
        return new FindCommand(commandArg);
    }
}
