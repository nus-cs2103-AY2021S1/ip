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
import duke.storage.TaskStorage;
import duke.task.Todo;
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
        if (commands.length <= Todo.COMMAND_LENGTH) {
            throw new DukeException("Ensure there is description for a todo item.");
        }
        String description = Utils.concatenate(commands, 1, commands.length);
        return new AddTodoOperation(description, list);
    }

    private AddDeadlineOperation createDeadlineOp(
            String[] commands, TaskList list) throws DukeException {
        if (commands.length <= Deadline.COMMAND_LENGTH) {
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
        if (commands.length <= Event.COMMAND_LENGTH) {
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
        if (!Utils.hasInteger(commands, 1)) {
            throw new DukeException("Ensure a number is passed after a delete command.");
        }
        int index = Integer.parseInt(commands[1]);
        if (!list.isValidIndex(index)) {
            throw new DukeException("The index you have passed in cannot be found in the list of tasks.");
        }
        return new DeleteOperation(list, index);
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
        switch(commands[0]) {
        case CommandType.BYE:
            return createExitOp(taskStorage, list);
        case CommandType.LIST:
            return createListOp(list);
        case CommandType.DONE:
            return createDoneOp(commands, list);
        case CommandType.TODO:
            return createTodoOp(commands, list);
        case CommandType.DEADLINE:
            return createDeadlineOp(commands, list);
        case CommandType.EVENT:
            return createEventOp(commands, list);
        case CommandType.DELETE:
            return createDeleteOp(commands, list);
        default:
            throw new DukeException("This command is not recognised unfortunately.");
        }
    }
}