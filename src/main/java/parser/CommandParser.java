package parser;

import java.lang.StringBuilder;
import java.util.Arrays;

import exception.DukeException;
import operation.Operation;
import operation.AddDeadlineOperation;
import operation.AddEventOperation;
import operation.AddTodoOperation;
import operation.DeleteOperation;
import operation.DoneOperation;
import operation.ExitOperation;
import operation.ListOperation;
import task.Deadline;
import task.Event;
import task.TaskStorage;

public class CommandParser {
    private static String concatenate(String[] arr, int start, int end) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";
        for (int i = start; i < end; i++) {
            builder.append(prefix);
            builder.append(arr[i]);
            prefix = " ";
        }
        return builder.toString();
    }

    private static int getIndexOf(String[] arr, String target) {
        return Arrays.asList(arr).indexOf(target);
    }

    private ExitOperation createExitOp() {
        return new ExitOperation();
    }

    private ListOperation createListOp(TaskStorage storage) {
        return new ListOperation(storage);
    }

    private DoneOperation createDoneOp(String[] commands, TaskStorage storage) throws DukeException {
        try {
            int index = Integer.parseInt(commands[1]);
            if (index <= storage.getCurrCapacity() && index > 0) {
                return new DoneOperation(storage, index);
            } else {
               throw new DukeException(
                       "The index you have passed in cannot be found in the list of tasks.");
            }
        } catch (NumberFormatException exception) {
            throw new DukeException("Ensure a number is passed after a done command.");
        }
    }

    private AddTodoOperation createTodoOp(String[] commands, TaskStorage storage) throws DukeException {
        if (commands.length <= 1) {
            throw new DukeException("Ensure there is description for a todo item.");
        }
        String description = concatenate(commands, 1, commands.length);
        return new AddTodoOperation(description, storage);
    }

    private AddDeadlineOperation createDeadlineOp(
            String[] commands, TaskStorage storage) throws DukeException {
        if (commands.length <= 2) {
            throw new DukeException("Ensure there is a description and a datetime for a deadline command.");
        }
        int splitIndex = getIndexOf(commands, Deadline.DEADLINE_BREAK);
        if (splitIndex == -1) {
            throw new DukeException("Ensure an indication of '/by' after a deadline command.");
        }
        String description = concatenate(commands, 1, splitIndex);
        String datetime = concatenate(commands, splitIndex + 1, commands.length);
        return new AddDeadlineOperation(description, datetime, storage);
    }

    private AddEventOperation createEventOp(String[] commands, TaskStorage storage) throws DukeException {
        if (commands.length <= 2) {
            throw new DukeException("Ensure there is a description and a time for an event command.");
        }
        int splitIndex = getIndexOf(commands, Event.EVENT_BREAK);
        if (splitIndex == -1) {
            throw new DukeException("Ensure an indication of '/at' after an event command.");
        }
        String description = concatenate(commands, 1, splitIndex);
        String datetime = concatenate(commands, splitIndex + 1, commands.length);
        return new AddEventOperation(description, datetime, storage);
    }

    private DeleteOperation createDeleteOp(String[] commands, TaskStorage storage) throws DukeException {
        try {
            int index = Integer.parseInt(commands[1]);
            if (index <= storage.getCurrCapacity() && index > 0) {
                return new DeleteOperation(storage, index);
            } else {
                throw new DukeException(
                        "The index you have passed in cannot be found in the list of tasks.");
            }
        } catch (NumberFormatException exception) {
            throw new DukeException("Ensure a number is passed after a delete command.");
        }
    }

    public Operation parse(String commandString, TaskStorage taskStorage) throws DukeException {
        String[] commands = commandString.split(" ");
        switch(commands[0]) {
            case CommandType.BYE:
                return createExitOp();
            case CommandType.LIST:
                return createListOp(taskStorage);
            case CommandType.DONE:
                return createDoneOp(commands, taskStorage);
            case CommandType.TODO:
                return createTodoOp(commands, taskStorage);
            case CommandType.DEADLINE:
                return createDeadlineOp(commands, taskStorage);
            case CommandType.EVENT:
                return createEventOp(commands, taskStorage);
            case CommandType.DELETE:
                return createDeleteOp(commands, taskStorage);
            default:
                throw new DukeException("This command is not recognised unfortunately.");
        }
    }
}