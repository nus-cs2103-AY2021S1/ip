package duke.parser;

import java.time.LocalDateTime;
import java.util.function.Function;

import duke.exception.DukeParseException;
import duke.operation.AddDeadlineOperation;
import duke.operation.AddEventOperation;
import duke.operation.AddTodoOperation;
import duke.operation.DeleteOperation;
import duke.operation.DoneOperation;
import duke.operation.ExitOperation;
import duke.operation.FindOperation;
import duke.operation.ListOperation;
import duke.operation.Operation;
import duke.storage.TaskStorage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.utils.Datetime;
import duke.utils.Utils;

/**
 * The class that converts commands passed into Duke into <code>Operations</code>.
 */
public class CommandParser {
    private ExitOperation createExitOp(TaskStorage storage, TaskList list) {
        return new ExitOperation(storage, list);
    }

    private ListOperation createListOp(TaskList list) {
        return new ListOperation(list);
    }

    private DoneOperation createDoneOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.DONE.isValidLength(commands.length)) {
            throw new DukeParseException("Ensure a number is passed after a done command.");
        }
        if (!Utils.hasInteger(commands, 1)) {
            throw new DukeParseException("Ensure a number is passed after a done command.");
        }
        int index = Integer.parseInt(commands[1]);

        return new DoneOperation(list, index);
    }

    private AddTodoOperation createTodoOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.TODO.isValidLength(commands.length)) {
            throw new DukeParseException("Ensure there is description for a todo item.");
        }
        String description = Utils.concatenate(commands, 1, commands.length);
        return new AddTodoOperation(description, list);
    }

    private AddDeadlineOperation createDeadlineOp(
            String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.DEADLINE.isValidLength(commands.length)) {
            throw new DukeParseException(
                    "Ensure there is a description and a datetime for a deadline command.");
        }
        int splitIndex = Utils.getIndexOf(commands, Deadline.DEADLINE_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeParseException("Ensure an indication of '/by' after a deadline command.");
        }
        String description = Utils.concatenate(commands, 1, splitIndex);
        String datetime = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime parsedDateTime = Datetime.parseDateTimeString(datetime, Deadline.DATE_FORMAT_INPUT);
        return new AddDeadlineOperation(description, parsedDateTime, list);
    }

    private AddEventOperation createEventOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.EVENT.isValidLength(commands.length)) {
            throw new DukeParseException("Ensure there is a description and a time for an event command.");
        }
        int splitIndex = Utils.getIndexOf(commands, Event.EVENT_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeParseException("Ensure an indication of '/at' after an event command.");
        }
        String description = Utils.concatenate(commands, 1, splitIndex);
        String time = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime parsedTime = Datetime.parseTimeString(time, Event.TIME_FORMAT_INPUT);
        return new AddEventOperation(description, parsedTime, list);
    }

    private DeleteOperation createDeleteOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.DELETE.isValidLength(commands.length)) {
            throw new DukeParseException("Ensure a number is passed after a delete command.");
        }
        if (!Utils.hasInteger(commands, 1)) {
            throw new DukeParseException("Ensure a number is passed after a delete command.");
        }
        int index = Integer.parseInt(commands[1]);

        return new DeleteOperation(list, index);
    }

    private FindOperation createFindOp(String[] commands, TaskList list) throws DukeParseException {
        if (CommandType.FIND.isValidLength(commands.length)) {
            throw new DukeParseException(
                    "Ensure a keyword is entered so that I can perform a search with it.");
        }
        String searchWord = Utils.concatenate(commands, 1, commands.length);
        return new FindOperation(list, searchWord);
    }

    /**
     * Parses the String given into an <code>Operation</code>.
     * @param commandString the <code>String</code> that has been input by the user into Duke.
     * @param list the <code>TaskList</code> to be operated on.
     * @param taskStorage the <code>TaskStorage</code> to be operated on,
     *                    if the <code>Operation</code> requires a save of the <code>TaskList</code>.
     * @return the parsed <code>Operation</code>.
     * @throws DukeParseException if the command cannot be recognised or is erroneous.
     */
    public Operation parse(String commandString, TaskList list, TaskStorage taskStorage)
            throws DukeParseException {
        String[] commands = commandString.split(" ");
        Function<CommandType, Boolean> isCommand = commandType ->
                commandType.getCommand().equals(commands[0]);

        if (isCommand.apply(CommandType.BYE)) {
            return createExitOp(taskStorage, list);
        } else if (isCommand.apply(CommandType.LIST)) {
            return createListOp(list);
        } else if (isCommand.apply(CommandType.DONE)) {
            return createDoneOp(commands, list);
        } else if (isCommand.apply(CommandType.TODO)) {
            return createTodoOp(commands, list);
        } else if (isCommand.apply(CommandType.DEADLINE)) {
            return createDeadlineOp(commands, list);
        } else if (isCommand.apply(CommandType.EVENT)) {
            return createEventOp(commands, list);
        } else if (isCommand.apply(CommandType.DELETE)) {
            return createDeleteOp(commands, list);
        } else if (isCommand.apply(CommandType.FIND)) {
            return createFindOp(commands, list);
        } else {
            throw new DukeParseException("This command is not recognised unfortunately.");
        }
    }
}
