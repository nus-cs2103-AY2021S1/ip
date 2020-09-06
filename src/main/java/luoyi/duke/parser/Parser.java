package luoyi.duke.parser;

import luoyi.duke.commands.ByeCommand;
import luoyi.duke.commands.Command;
import luoyi.duke.commands.DeadlineCommand;
import luoyi.duke.commands.DeleteCommand;
import luoyi.duke.commands.DoneCommand;
import luoyi.duke.commands.EventCommand;
import luoyi.duke.commands.FindCommand;
import luoyi.duke.commands.ListCommand;
import luoyi.duke.commands.ToDoCommand;
import luoyi.duke.common.Message;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.exception.DukeUnrecognizedArgumentException;

/**
 * Parser class to handle command string parsing.
 */
public class Parser {
    /**
     * Parse a command and returns the corresponding command object.
     *
     * @param commandStr Command string.
     * @return Command object to execute.
     * @throws DukeIllegalArgumentException If the command string is invalid.
     * @see Command For details of command objects.
     */
    public static Command parse(String commandStr) throws DukeIllegalArgumentException {
        if (commandStr.matches("^list.*")) {
            // Found list command
            return parseListCommand(commandStr);
        } else if (commandStr.matches("^done.*")) {
            // Found done command
            return parseDoneCommand(commandStr);
        } else if (commandStr.matches("^todo.*")) {
            // Found todo command
            return parseToDoCommand(commandStr);
        } else if (commandStr.matches("^deadline.*")) {
            // Found deadline command
            return parseDeadlineCommand(commandStr);
        } else if (commandStr.matches("^event.*")) {
            // Handle event command
            return parseEventCommand(commandStr);
        } else if (commandStr.matches("^delete.*")) {
            // Handle delete command
            return parseDeleteCommand(commandStr);
        } else if (commandStr.matches("^find.*")) {
            // Handle find command
            return parseFindCommand(commandStr);
        } else if (commandStr.equals("bye")) {
            return ByeCommand.getByeCommand();
        }
        throw new DukeUnrecognizedArgumentException(Message.ERR_WRONG_CMD.toString());
    }

    private static FindCommand parseFindCommand(String commandStr) {
        if (!commandStr.matches("^find .*")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_FIND_CMD.toString());
        }
        String searchString = commandStr.split(" ", 2)[1];
        return FindCommand.getFindCommand(searchString);
    }

    private static DeleteCommand parseDeleteCommand(String commandStr) {
        if (!commandStr.matches("^delete -?[0-9]+$")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_DELETE_CMD.toString());
        }
        int index = Integer.parseInt(commandStr.split(" ")[1]);
        return DeleteCommand.getDeleteCommand(index);
    }

    private static EventCommand parseEventCommand(String commandStr) {
        if (!commandStr.matches("^event .* /at .*")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_EVENT_CMD.toString());
        }
        String[] params = commandStr.split(" ", 2)[1].split(" /at ");
        return EventCommand.getEventCommand(params[0], params[1]);
    }

    private static DeadlineCommand parseDeadlineCommand(String commandStr) {
        if (!commandStr.matches("^deadline .* /by .*")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_DEADLINE_CMD.toString());
        }
        String[] params = commandStr.split(" ", 2)[1].split(" /by ");
        return DeadlineCommand.getDeadlineCommand(params[0], params[1]);
    }

    private static ToDoCommand parseToDoCommand(String commandStr) {
        if (!commandStr.matches("^todo .*")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_TODO_CMD.toString());
        }
        String description = commandStr.split(" ", 2)[1];
        return ToDoCommand.getToDoCommand(description);
    }

    private static DoneCommand parseDoneCommand(String commandStr) {
        if (!commandStr.matches("^done -?[0-9]+$")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_DONE_CMD.toString());
        }
        int index = Integer.parseInt(commandStr.split(" ", 2)[1]);
        return DoneCommand.getDoneCommand(index);
    }

    private static ListCommand parseListCommand(String commandStr) {
        if (commandStr.equals("list")) {
            return ListCommand.getListCommand(null);
        } else if (!commandStr.matches("^list .+")) {
            throw new DukeIllegalArgumentException(
                    Message.ERR_WRONG_LIST_CMD.toString());
        }
        return ListCommand.getListCommand(commandStr.split(" ", 2)[1]);
    }


}
