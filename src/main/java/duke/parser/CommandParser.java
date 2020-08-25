package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.operation.Operation;
import duke.operation.AddDeadlineOperation;
import duke.operation.AddEventOperation;
import duke.operation.AddTodoOperation;
import duke.operation.DeleteOperation;
import duke.operation.DoneOperation;
import duke.operation.ExitOperation;
import duke.operation.ListOperation;
import duke.operation.FindOperation;
import duke.storage.TaskStorage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.utils.Datetime;
import duke.utils.Utils;

/**
 * The class that converts commands passed into Duke into Operations.
 */
public class CommandParser {
    private ExitOperation createExitOp(TaskStorage storage, TaskList list) {
        return new ExitOperation(storage, list);
    }

    private ListOperation createListOp(TaskList list) {
        return new ListOperation(list);
    }

    private DoneOperation createDoneOp(String[] commands, TaskList list) throws DukeException {
        if (CommandType.DONE.isLengthSmaller(commands.length)) {
            throw new DukeException("Ensure a number is passed after a done command.");
        }
        if (!Utils.hasInteger(commands, 1)) {
            throw new DukeException("Ensure a number is passed after a done command.");
        }
        int index = Integer.parseInt(commands[1]);
        if (!list.isValidIndex(index)) {
            throw new DukeException("The index you have passed in cannot be found in the list of tasks.");
        }
        return new DoneOperation(list, index);
    }

    private AddTodoOperation createTodoOp(String[] commands, TaskList list) throws DukeException {
        if (CommandType.TODO.isLengthSmaller(commands.length)) {
            throw new DukeException("Ensure there is description for a todo item.");
        }
        String description = Utils.concatenate(commands, 1, commands.length);
        return new AddTodoOperation(description, list);
    }

    private AddDeadlineOperation createDeadlineOp(
            String[] commands, TaskList list) throws DukeException {
        if (CommandType.DEADLINE.isLengthSmaller(commands.length)) {
            throw new DukeException("Ensure there is a description and a datetime for a deadline command.");
        }
        int splitIndex = Utils.getIndexOf(commands, Deadline.DEADLINE_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeException("Ensure an indication of '/by' after a deadline command.");
        }
        String description = Utils.concatenate(commands, 1, splitIndex);
        String datetime = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime parsedDateTime = Datetime.parseDateTimeString(datetime, Deadline.DATE_FORMAT_INPUT);
        return new AddDeadlineOperation(description, parsedDateTime, list);
    }

    private AddEventOperation createEventOp(String[] commands, TaskList list) throws DukeException {
        if (CommandType.EVENT.isLengthSmaller(commands.length)) {
            throw new DukeException("Ensure there is a description and a time for an event command.");
        }
        int splitIndex = Utils.getIndexOf(commands, Event.EVENT_BREAK);
        if (splitIndex == Utils.INDEX_NOT_FOUND) {
            throw new DukeException("Ensure an indication of '/at' after an event command.");
        }
        String description = Utils.concatenate(commands, 1, splitIndex);
        String time = Utils.concatenate(commands, splitIndex + 1, commands.length);
        LocalDateTime parsedTime = Datetime.parseTimeString(time, Event.TIME_FORMAT_INPUT);
        return new AddEventOperation(description, parsedTime, list);
    }

    private DeleteOperation createDeleteOp(String[] commands, TaskList list) throws DukeException {
        if (CommandType.DELETE.isLengthSmaller(commands.length)) {
            throw new DukeException("Ensure a number is passed after a delete command.");
        }
        if (!Utils.hasInteger(commands, 1)) {
            throw new DukeException("Ensure a number is passed after a delete command.");
        }
        int index = Integer.parseInt(commands[1]);
        if (!list.isValidIndex(index)) {
            throw new DukeException("The index you have passed in cannot be found in the list of tasks.");
        }
        return new DeleteOperation(list, index);
    }

    private FindOperation createFindOp(String[] commands, TaskList list) throws DukeException {
        if (CommandType.FIND.isLengthSmaller(commands.length)) {
            throw new DukeException("Ensure a keyword is entered so that I can perform a search with it.");
        }
        String searchWord = Utils.concatenate(commands, 1, commands.length);
        return new FindOperation(list, searchWord);
    }

    /**
     * Parses the String given into an Operation object.
     * @param commandString the String that has been input by the user into Duke.
     * @param list the TaskList to be operated on.
     * @param taskStorage the TaskStorage object to be operated on, if the operation requires
     *                    a save of the TaskList.
     * @return the parsed Operation object.
     * @throws DukeException if the command cannot be recognised or is erroneous.
     */
    public Operation parse(String commandString, TaskList list, TaskStorage taskStorage)
            throws DukeException {
        String[] commands = commandString.split(" ");
        if (CommandType.BYE.getCommand().equals(commands[0])) {
            return createExitOp(taskStorage, list);
        } else if (CommandType.LIST.getCommand().equals(commands[0])) {
            return createListOp(list);
        } else if (CommandType.DONE.getCommand().equals(commands[0])) {
            return createDoneOp(commands, list);
        } else if (CommandType.TODO.getCommand().equals(commands[0])) {
            return createTodoOp(commands, list);
        } else if (CommandType.DEADLINE.getCommand().equals(commands[0])) {
            return createDeadlineOp(commands, list);
        } else if (CommandType.EVENT.getCommand().equals(commands[0])) {
            return createEventOp(commands, list);
        } else if (CommandType.DELETE.getCommand().equals(commands[0])) {
            return createDeleteOp(commands, list);
        } else if (CommandType.FIND.getCommand().equals(commands[0])) {
            return createFindOp(commands, list);
        } else {
            throw new DukeException("This command is not recognised unfortunately.");
        }
    }
}